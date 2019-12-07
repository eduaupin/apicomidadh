package br.com.digitalhouse.foodparty.views.eventos;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import br.com.digitalhouse.foodparty.R;
import br.com.digitalhouse.foodparty.model.Evento;
import br.com.digitalhouse.foodparty.model.Participante;
import br.com.digitalhouse.foodparty.model.Prato;
import br.com.digitalhouse.foodparty.viewmodel.CriarEventoViewModel;
import br.com.digitalhouse.foodparty.views.adapter.ParticipantesNovoEventoAdapter;
import br.com.digitalhouse.foodparty.views.adapter.PratosNovoEventoAdapter;
import br.com.digitalhouse.foodparty.views.home.HomeActivity;
import br.com.digitalhouse.foodparty.views.interfaces.ClickPratoAdicionar;
import br.com.digitalhouse.foodparty.views.pratos.DetalhesDoPratoActivity;

import static br.com.digitalhouse.foodparty.views.eventos.AdicionarPessoaAoEventoActivity.PARTICIPANTES_KEY;
import static br.com.digitalhouse.foodparty.views.home.HomeFragment.PRATO_KEY;

public class CriarEventoActivity extends AppCompatActivity implements ClickPratoAdicionar {
    public static final int ADICIONAR_PRATO_REQUEST = 1;
    public static final int ADICIONAR_PARTICIPANTE_REQUEST = 2;
    public static final int GALLERY_REQUEST_CODE = 3;
    private static final int REQUEST_STORAGE_PERMISSION = 100;
    private Toolbar toolbar;
    private TextInputLayout inputTitulo;
    private TextInputLayout inputData;
    private TextInputLayout inputHora;
    private TextInputLayout inputLocal;
    private TextView semParticipantes;
    private TextView semPratos;
    private Button buttonAdicionarParticipantes;
    private Button buttonAdicionarPratos;
    private MaterialButton buttonSalvarEvento;
    private RecyclerView recyclerParticipantes;
    private RecyclerView recyclerPratos;
    private ParticipantesNovoEventoAdapter participantesAdapter;
    private PratosNovoEventoAdapter pratosAdapter;
    private List<Participante> participantes = new ArrayList<>();
    private List<Prato> pratos = new ArrayList<>();
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private CriarEventoViewModel viewModel;
    private ImageView imgEvento;
    private String absolutePathImage;

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
    int currentMinute = calendar.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_evento);

        initViews();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Novo Evento");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        buttonAdicionarParticipantes.setOnClickListener(view -> {
            Intent intent = new Intent(CriarEventoActivity.this, AdicionarPessoaAoEventoActivity.class);
            startActivityForResult(intent, ADICIONAR_PARTICIPANTE_REQUEST);
        });

        buttonAdicionarPratos.setOnClickListener(view -> {
            Intent intent = new Intent(CriarEventoActivity.this, AdicionarPratosAoEventoActivity.class);
            startActivityForResult(intent, ADICIONAR_PRATO_REQUEST);
        });

        displayData();

        imgEvento.setOnClickListener(view -> {
            pickFromGallery();
        });

        inputData.getEditText().setOnClickListener(view -> {
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        inputHora.getEditText().setOnClickListener(view -> {
            timePickerDialog.show();
        });

        buttonSalvarEvento.setOnClickListener(view -> {
            String titulo = inputTitulo.getEditText().getText().toString();
            String dataEvento = inputData.getEditText().getText().toString();
            String horaEvento = inputHora.getEditText().getText().toString();
            String enderecoEvento = inputLocal.getEditText().getText().toString();

            if (titulo.isEmpty()) {
                inputTitulo.setError(getString(R.string.validacao_evento_nome));
            }

            if (absolutePathImage == null) {
                Toast.makeText(this, "Escolha uma imagem para seu evento", Toast.LENGTH_SHORT).show();
            }

            Evento novoEvento = new Evento(absolutePathImage, titulo, dataEvento, horaEvento, enderecoEvento, pratos, participantes);
            viewModel.salvarEvento(novoEvento);

            startActivity(new Intent(this, HomeActivity.class));
            finish();

        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar_novo_evento);
        inputTitulo = findViewById(R.id.input_novo_evento_titulo);
        inputData = findViewById(R.id.input_novo_evento_data);
        inputHora = findViewById(R.id.input_novo_evento_hora);
        inputLocal = findViewById(R.id.input_novo_evento_local);
        imgEvento = findViewById(R.id.image_novo_evento_foto);
        semParticipantes = findViewById(R.id.text_novo_evento_sem_participantes);
        semPratos = findViewById(R.id.text_novo_evento_sem_pratos);
        buttonAdicionarParticipantes = findViewById(R.id.button_novo_participante);
        buttonAdicionarPratos = findViewById(R.id.button_novo_pratos);
        buttonSalvarEvento = findViewById(R.id.button_novo_evento_salvar);
        recyclerParticipantes = findViewById(R.id.recycler_novo_evento_participantes);
        recyclerPratos = findViewById(R.id.recycler_novo_evento_pratos);
        participantesAdapter = new ParticipantesNovoEventoAdapter(participantes, semParticipantes);
        recyclerParticipantes.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerParticipantes.setAdapter(participantesAdapter);
        pratosAdapter = new PratosNovoEventoAdapter(pratos, semPratos, this);
        recyclerPratos.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerPratos.setAdapter(pratosAdapter);
        viewModel = ViewModelProviders.of(this).get(CriarEventoViewModel.class);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayData() {
        datePickerDialog = new DatePickerDialog(CriarEventoActivity.this,
                (datePicker, year, month, day) -> {
                    inputData.getEditText().setText(String.format(Locale.GERMAN, "%d/%d/%d", day, month + 1, year));
                }, year, month, dayOfMonth);

        timePickerDialog = new TimePickerDialog(CriarEventoActivity.this, (timePicker, hourOfDay, minutes) -> {
            inputHora.getEditText().setText(String.format(Locale.GERMAN, "%02d:%02d", hourOfDay, minutes));
        }, currentHour, currentMinute, true);
    }

    @Override
    public void onClickAdicionarPrato(Prato prato) {
        Intent intent = new Intent(CriarEventoActivity.this, DetalhesDoPratoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(PRATO_KEY, prato);
        intent.putExtras(bundle);
        startActivityForResult(intent, ADICIONAR_PRATO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Prato adicionado", Toast.LENGTH_SHORT).show();
            if (data != null) {
                Prato prato = data.getParcelableExtra(PRATO_KEY);
                pratos.add(prato);
                pratosAdapter.notifyDataSetChanged();
            }
        }

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Participantes adicionados", Toast.LENGTH_SHORT).show();
            if (data != null) {
                participantes.addAll(data.getParcelableArrayListExtra(PARTICIPANTES_KEY));
                participantesAdapter.notifyDataSetChanged();
            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK) {
            Toast.makeText(this, "Imagem adicionada", Toast.LENGTH_SHORT).show();
            if (data != null) {
                Uri selectedImage = data.getData();

                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                absolutePathImage = cursor.getString(columnIndex);

                cursor.close();

                Picasso.get().load(new File(absolutePathImage)).fit().centerCrop().into(imgEvento);
                imgEvento.setBackgroundColor(getResources().getColor(R.color.colorBackgroundPage));
                imgEvento.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }

    }

    private void pickFromGallery() {

        String[] permission = Arrays.asList(Manifest.permission.READ_EXTERNAL_STORAGE).toArray(new String[0]);

        if (isPermissionGranted()) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            String[] mimeTypes = {"image/jpeg", "image/png"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            startActivityForResult(intent, GALLERY_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(this,
                    permission, REQUEST_STORAGE_PERMISSION);
        }

    }

    private boolean isPermissionGranted() {
        return ContextCompat.checkSelfPermission(CriarEventoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_STORAGE_PERMISSION: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    pickFromGallery();
                } else {
                    Toast.makeText(this, "Você precisa autorizar o envio de uma foto.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    public int getCameraPhotoOrientation(Context context, Uri imageUri,
                                         String imagePath) {
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

            Log.i("RotateImage", "Exif orientation: " + orientation);
            Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }
}

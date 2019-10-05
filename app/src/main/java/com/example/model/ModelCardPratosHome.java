package com.example.model;

import android.widget.ImageView;

public class ModelCardPratosHome {

    private ImageView imgPrato;
    private String txtPrato;


    public ModelCardPratosHome( String txtPrato) {
        this.txtPrato = txtPrato;
    }


    public void setImgPrato(ImageView imgPrato) {
        this.imgPrato = imgPrato;
    }

    public String getTxtPrato() {
        return txtPrato;
    }

    public void setTxtPrato(String txtPrato) {
        this.txtPrato = txtPrato;
    }
}

package com.astrocure.astrologer.models;

public class DocumentModel {
    int img;
    String docName;

    public DocumentModel(int img, String docName) {
        this.img = img;
        this.docName = docName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}

package com.gridants.photoapp;

/**
 * Created by prats on 10/12/15.
 */
public class PhotoModel {

    private String filePath;
    private Integer lastModified;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getLastModified() {
        return lastModified;
    }

    public void setLastModified(Integer lastModified) {
        this.lastModified = lastModified;
    }


}

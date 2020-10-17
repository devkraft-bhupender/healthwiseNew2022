package com.hwi.health.Models;

import java.io.Serializable;

/**
 * Created by PAWAN on 01-08-2017.
 */

public class Upload_file_data implements Serializable {
    String file_name,file_url,treat_text;

    public Upload_file_data(String file_name, String file_url, String treat_text) {
        this.file_name = file_name;
        this.file_url = file_url;
        this.treat_text = treat_text;
    }

    public String getTreat_text() {
        return treat_text;
    }

    public void setTreat_text(String treat_text) {
        this.treat_text = treat_text;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }
}

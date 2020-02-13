package br.com.desktop.login.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public class Logged {
    @Column(name="DATE_CREATED")
    @ApiModelProperty(hidden = true)
    private Date create;
    @Column(name="DATE_LAST_UPDATE")
    @ApiModelProperty(hidden = true)
    private Date modify;

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify = modify;
    }


    public void createLogged(Date createdDate){
        if(createdDate != null){
            setCreate(createdDate);
            setModify(new Date());
        }else{
            setCreate(new Date());
        }
    }
}


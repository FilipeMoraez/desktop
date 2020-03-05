package br.com.desktop.login.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
@Getter
@Setter
public class Logged {
    @Column(name="DATE_CREATED")
    @ApiModelProperty(hidden = true)
    private Date create;

    @Column(name="DATE_LAST_UPDATE")
    @ApiModelProperty(hidden = true)
    private Date modify;

    public void createLogged(Date createdDate){
        if(createdDate != null){
            setCreate(createdDate);
            setModify(new Date());
        }else{
            setCreate(new Date());
        }
    }
}


package com.example.demo.mgr.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description 邮件内容bo
 * @date 2021/3/3 11:27
 **/
@Data
@NoArgsConstructor
public class EmailContentBo  {


    private String email;

    private String title;

    private String content;

    public EmailContentBo(String email,String title){
        this.email=email;
        this.title=title;
    }
    public void createContent(String template,String ... content){
        this.content=String.format(template, content);
    }
}

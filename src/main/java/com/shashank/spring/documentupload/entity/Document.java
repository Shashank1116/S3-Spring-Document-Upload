package com.shashank.spring.documentupload.entity;

import com.shashank.spring.documentupload.pojo.DocumentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name="DOCUMENT")
public class Document  {

    private static final Long serialVersionUId = 6524768694427900663L;

    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;



    @Column(name = "DOCUMENT_TYPE")
    private String categoryName;

    @Column(name = "RESOURCE_URL")
    private String resourceURL;


    public DocumentDTO toDTO(){
        DocumentDTO dto = new DocumentDTO();
        dto.setCategory(this.getCategoryName());
        dto.setId(this.getId());
        return dto;
    }


}

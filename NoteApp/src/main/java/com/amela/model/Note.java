package com.amela.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

@Entity

//@NamedStoredProcedureQuery(
//        name = "addNote",
//        procedureName = "sp_AddNote",
//        parameters = {
//                @StoredProcedureParameter(name = "firstName", mode = ParameterMode.IN, type = String.class),
//                @StoredProcedureParameter(name = "lastName", mode = ParameterMode.IN, type = String.class),
//                @StoredProcedureParameter(name = "typeid", mode =ParameterMode.IN,type = int.class)
//        }
//)
@NamedStoredProcedureQuery(
        name = "getNote",
        procedureName = "sp_ListNote"
)
@NamedQuery(name = "findProductsWithId",
        query = "select n from Note n where  n.noteType=:notetype_id")
@Table
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "title must not be blank")
    @Size(min = 1, max = 255,message = "title size must be between 1 and 255")
    private String title;
    @NotBlank(message = "content must not be blank")
    @Size(min = 1, max = 255,message = "content size must be between 1 and 255")
    private String content;

    @ManyToOne
    @JoinColumn(name = "notetype_id")
    private NoteType noteType;

    public Note() {
    }

    public Note(Integer id, String title, String content, NoteType noteType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.noteType = noteType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }
}

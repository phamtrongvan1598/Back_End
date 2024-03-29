package com.strongman.ssm.model;

import javax.persistence.*;

@Entity
@Table(name = "notess")
public class Notes {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String title;
        private String content;
        public Notes(){}
        public Notes(String title , String content){
            this.title = title;
            this.content = content;
        }
    @Override
    public String toString() {
        return String.format("Notes[id=%d, title='%s', content%s']", id, title, content);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}

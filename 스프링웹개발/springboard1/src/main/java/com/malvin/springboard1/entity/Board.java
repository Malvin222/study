package com.malvin.springboard1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name="BOARD")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long no;

    @Column(length = 60)
    private String writer;

    @Column(length = 300)
    private String title;

    @Column @Lob
    private String content;

    @Column
    private int readcount;

    @CreationTimestamp
    private Date writeTime;

    @CreationTimestamp
    private Date editTime;
}

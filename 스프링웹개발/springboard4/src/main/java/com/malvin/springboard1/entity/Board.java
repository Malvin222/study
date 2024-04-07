package com.malvin.springboard1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity @Table(name = "BOARD")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long no;

    @Column(length = 60)
    private String writer;

    @Column(length = 300)
    private String title;

    @Column @Lob
    private String content;

    @Column(length = 20)
    private String password;

    @Column
    private int readcount;

    @CreationTimestamp
    private Date writeTime;

    @UpdateTimestamp
    private Date editTime;

    //계층형 게시판을 위한 상태값
    @Column
    private Long grp;

    @Column
    private long seq, dep;

}

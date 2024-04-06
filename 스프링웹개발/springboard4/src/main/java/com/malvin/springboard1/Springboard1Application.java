package com.malvin.springboard1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@Slf4j
@SpringBootApplication
public class Springboard1Application {

    public static void main(String[] args) {

        generateDatabaseFile();
        SpringApplication.run(Springboard1Application.class, args);
    }

    public static void generateDatabaseFile(){
        File useDirectory = new File(System.getProperty("user.home"));
        File target = new File(useDirectory,"test.mv.db");
        if(!target.exists()){
            try{
                if(target.createNewFile()){
                    log.info("데이터베이스 생성 성공");
                }else{
                    log.info("이미 데이터베이스가 생성되어 있음");
                }
            }catch (Exception e){
                log.error("데이터베이스 생성 실패",e);
            }
        }
    }

}

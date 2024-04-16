package com.ltp.gradesubmission.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

// 声明这是一个 JPA 实体类
@Entity
@Table(name = "student")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name", nullable = false)
    private String name;
    
    @NonNull
    @Column(name = "birth_date" , nullable = false)
    private LocalDate birthDate;

    //one student has many grades
    //grades 列表中的每个元素都会被映射到 Grade 实体类中的 student 属性上
    //cascade = CascadeType.ALL表示对 grades 属性进行的所有操作都会级联到关联的 Grade 实体
    @JsonIgnore
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Grade> grades;
}

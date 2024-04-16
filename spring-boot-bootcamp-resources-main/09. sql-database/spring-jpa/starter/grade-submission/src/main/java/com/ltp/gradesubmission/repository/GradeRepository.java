package com.ltp.gradesubmission.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ltp.gradesubmission.entity.Grade;
import java.util.List;


public interface GradeRepository extends CrudRepository<Grade,Long>{
    //根据 Spring Data JPA 的方法命名规则，findBy 后面跟着属性名（首字母大写），表示按照该属性进行查询。所以，findByStudentId 方法会自动生成一个根据 studentId 属性查询的方法。
    //findByXXXorXXX
    //findByXXXandXXX
    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);
    @Transactional
    void deleteByStudentIdAndCourseId(Long studentId, Long courseId);
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByCourseId(Long courseId);
}
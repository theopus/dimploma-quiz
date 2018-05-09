package com.ira.quizplatform.security;

import com.ira.quizplatform.entity.Student;
import com.ira.quizplatform.entity.Teacher;
import com.ira.quizplatform.repository.StudentRepo;
import com.ira.quizplatform.repository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersSecurityProfile implements UserDetailsService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private TeacherRepo teacherRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = teacherRepo.findByName(username);
        if (teacher != null) {
            List<GrantedAuthority> auth = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_TEACHER");
            return org.springframework.security.core.userdetails.User
                    .withDefaultPasswordEncoder()
                    .username(username)
                    .password(teacher.getPassword())
                    .authorities(auth)
                    .build();
        }

        Student student = studentRepo.findByName(username);
        if (student != null) {
            List<GrantedAuthority> auth = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_STUDENT");
            return org.springframework.security.core.userdetails.User
                    .withDefaultPasswordEncoder()
                    .username(username)
                    .password(student.getPassword())
                    .authorities(auth)
                    .build();
        }

        return null;
    }
}

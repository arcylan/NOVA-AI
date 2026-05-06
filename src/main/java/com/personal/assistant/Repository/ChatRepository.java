package com.personal.assistant.Repository;

import com.personal.assistant.Model.Chat;
import com.personal.assistant.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {

}

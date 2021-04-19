package com.estudo.user.entrypoint

import com.estudo.user.Entity.User
import com.estudo.user.usecase.SearchAllUsers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

class UserControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc


    SearchAllUsers searchAllUsers = Mock();

    UserController userController = new UserController()

    def setup(){
        userController.searchAllUsers = searchAllUsers;
    }
    def "Metodo Get funciona corretamente"() {
        given: "A requisição é feita"
        User user =  new User()

        String email = "testeemail@teste.com"
        String nome = "Hiago"
        String id = UUID.randomUUID().toString()
        String password = "minhasenha"

        user.setEmail(email);
        user.setName(nome)
        user.setId(id)
        user.setPassword(password)

        List users = new ArrayList()
        users.add(user)


        and: "Mock SearchAllUsers"
        searchAllUsers.execute()>>{
            return  users
        }

        when: "Chama o entrypoint"
        ResponseEntity result = userController.getVaccineByName()

        then: "Retorna uma lista de usuários"
        result.getStatusCode().value() == 1000

    }

}

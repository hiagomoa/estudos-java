package user.entrypoint

import com.estudo.user.Entity.User
import com.estudo.user.entrypoint.UserController
import com.estudo.user.usecase.IncludeUser
import com.estudo.user.usecase.SearchAllUsers
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import javax.ws.rs.core.MediaType

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

class UserControllerTest extends Specification {
    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    SearchAllUsers searchAllUsers = Mock();
    IncludeUser includeUser = Mock()

    UserController userController = new UserController()

    def setup(){
        userController.searchAllUsers = searchAllUsers;
        userController.includeUser = includeUser;
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

        when: "Chama o entrypoint"
//        MvcResult result = mockMvc.perform(get("/users/all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
//        MvcResult result = mockMvc.perform(post("/users/all1")
//                .content(objectMapper.writeValueAsString(user))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andReturn()
        MvcResult mvcResult = mockMvc.perform(post("/users/all1")).andExpect(status().isOk()).andReturn();

        then: "Retorna uma lista de usuários"
        includeUser.execute(user)>>{
            return  users
        }

        result

    }
}

package user.entrypoint

import com.estudo.user.Entity.User
import com.estudo.user.UserApplication
import com.estudo.user.external.database.GetUserByEmailAndPasswordImpl
import com.estudo.user.usecase.IncludeUser
import com.estudo.user.usecase.SearchAllUsers
import com.estudo.user.usecase.UpdateUser
import com.estudo.user.usecase.ValidateInclusionUser
import com.estudo.user.viewModel.ViewModelAutenticate
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType

import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import spock.lang.Specification


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@SpringBootTest(classes= UserApplication)
@AutoConfigureMockMvc
class UserControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    @SpringBean
    SearchAllUsers searchAllUsers = Mock();

    @SpringBean
    IncludeUser includeUser = Mock()

    @SpringBean
    UpdateUser updateUser = Mock()

    @SpringBean
    GetUserByEmailAndPasswordImpl getUserByEmailAndPassword = Mock()

    @SpringBean
    ValidateInclusionUser validateInclusionUser = Mock();

    def "Metodo Get funciona corretamente"() {
        given: "A requisição é feita"

        ObjectMapper objectMapper = new ObjectMapper();
        User user =  new User()
        String email = "testeemail@teste.com"
        String nome = "Hiago"
        String id = UUID.randomUUID().toString()
        String password = "minhasenha"

        user.setEmail(email);
        user.setName(nome)
        user.setId(id)
        user.setPassword(password)

        List<User> users = new ArrayList<>()
        users.add(user)

        when: "Chama o entrypoint"
        MvcResult result = mockMvc.perform(get("/users/all")).andExpect(status().isOk()).andReturn();

        then: "Retorna uma lista de usuários"
        1 * searchAllUsers.execute()>>{
            return users
        }
        List resultFinal = objectMapper.readValue(result.response.contentAsString, List)

        and: "Comparar os resultados"
        result.response.status == 200
        resultFinal[0].name == nome
        resultFinal[0].email == email
        resultFinal[0].password == password
    }

    def "Metodo Post"(){
        given: "Fazer a requisicao"
        ObjectMapper objectMapper = new ObjectMapper();
        String email = "testeemail@teste.com"
        String nome = "Hiago"
        String id = UUID.randomUUID().toString()
        String password = "minhasenha"
        User user =  new User(id, nome, email, password)
        String teste = objectMapper.writeValueAsString(user)


        when: "do POST to register user"
        MvcResult result = mockMvc.perform(post("/users/all1")
            .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user))).andReturn()

        then: "request token use case should be called and return"
        validateInclusionUser.execute(_ as String)>>true

        and: "mock"
        1 * includeUser.execute(_ as User)>>{
            return user
        }


        and: "status should be"
        result.response.status == 200
    }

    def "Metodo Post update"(){
        given: "Fazer a requisicao"
        ObjectMapper objectMapper = new ObjectMapper();
        String email = "testeemail@teste.com"
        String nome = "Hiago"
        String id = UUID.randomUUID().toString()
        String password = "minhasenha"
        User user =  new User(id, nome, email, password)
        String teste = objectMapper.writeValueAsString(user)


        when: "do POST to register user"
        MvcResult result = mockMvc.perform(post("/users/update/123")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user))).andReturn()

        then: "request token use case should be called and return"
        1 * updateUser.execute(_ as String,_ as User)>>{
            return
        }

        and: "status should be"
        result.response.status == 200
    }

    def "Metodo Post auth"(){
        given: "Fazer a requisicao"
        ObjectMapper objectMapper = new ObjectMapper();
        String email = "testeemail@teste.com"
        String nome = "Hiago"
        String id = UUID.randomUUID().toString()
        String password = "minhasenha"
        User user =  new User(id, nome, email, password)

        ViewModelAutenticate viewModelAutenticate = new ViewModelAutenticate()
        viewModelAutenticate.setPassword("teste")
        viewModelAutenticate.setEmail("teste@email")


        when: "do POST to register user"
        MvcResult result = mockMvc.perform(post("/users/auth")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(viewModelAutenticate))).andReturn()

        then: "request token use case should be called and return"
        1 * getUserByEmailAndPassword.execute(_ as String, _ as String)>>{
            return user
        }
        Object resultFinal = objectMapper.readValue(result.response.contentAsString, Object)

        and: "status should be"
        result.response.status == 200
        resultFinal.name == nome
        resultFinal.id == id
        resultFinal.email == email
    }

    def "Caso de erro Metodo Post auth"(){
        given: "Fazer a requisicao"
        ObjectMapper objectMapper = new ObjectMapper();
        String email = "testeemail@teste.com"
        String nome = "Hiago"
        String id = UUID.randomUUID().toString()
        String password = "minhasenha"
        User user =  new User(id, nome, email, password)

        ViewModelAutenticate viewModelAutenticate = new ViewModelAutenticate()
        viewModelAutenticate.setPassword("teste")
        viewModelAutenticate.setEmail("teste@email")


        when: "do POST to register user"
        MvcResult result = mockMvc.perform(post("/users/auth")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(viewModelAutenticate))).andReturn()

        then: "request token use case should be called and return"
        1 * getUserByEmailAndPassword.execute(_ as String, _ as String)>>{
            return new User()
        }

        and: "status should be"
        result.response.status == 400

    }
}



package com.example.Betting;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Betting.controller.TypesController;
import com.example.Betting.model.Types;
import com.example.Betting.service.TypesService;

@RunWith(SpringRunner.class)
@WebMvcTest(TypesController.class)
public class TypesControllerTests {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TypesService typesService;
    
    @Test
    public void whenGetTypes_thenArrayOfTypes()
      throws Exception {
        
    	Collection<Types> types = new ArrayList<Types>();
    	types.add(new Types((long) 1, "Football", "1", "X", "2", "1X", "X2", "12", null));
    	types.add(new Types((long) 2, "Basketball", "1X", "X2", ">=200p", "<200p", "1H10", "2H10", null));
    	given(typesService.findAllTypes()).willReturn(types);

        mvc.perform(MockMvcRequestBuilders.get("/api/types")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().json("[\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 1,\r\n" + 
          		"        \"name\": \"Football\",\r\n" + 
          		"        \"type1\": \"1\",\r\n" + 
          		"        \"type2\": \"X\",\r\n" + 
          		"        \"type3\": \"2\",\r\n" + 
          		"        \"type4\": \"1X\",\r\n" + 
          		"        \"type5\": \"X2\",\r\n" + 
          		"        \"type6\": \"12\"\r\n" + 
          		"    },\r\n" + 
          		"    {\r\n" + 
          		"        \"id\": 2,\r\n" + 
          		"        \"name\": \"Basketball\",\r\n" + 
          		"        \"type1\": \"1X\",\r\n" + 
          		"        \"type2\": \"X2\",\r\n" + 
          		"        \"type3\": \">=200p\",\r\n" + 
          		"        \"type4\": \"<200p\",\r\n" + 
          		"        \"type5\": \"1H10\",\r\n" + 
          		"        \"type6\": \"2H10\"\r\n" + 
          		"    }\r\n" + 
          		"]"));
    }
   
}

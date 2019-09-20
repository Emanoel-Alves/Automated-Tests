package api.test.locadora;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application.properties")
class LocadoraApplicationTests {
	
	@DisplayName("Teste1 ")
	@Test
	void test() {
		Assert.assertTrue(false);
	}
	
	@Test
	public void testeExep() throws Exception{
		
	}

}

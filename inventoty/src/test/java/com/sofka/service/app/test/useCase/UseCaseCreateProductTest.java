package com.sofka.service.app.test.useCase;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sofka.service.app.domain.useCase.CreateProductUseCase;
import com.sofka.service.app.infraestructure.drivenAdapter.entity.Producto;
import com.sofka.service.app.infraestructure.drivenAdapter.repository.IProductoRepository;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UseCaseCreateProductTest {

	private CreateProductUseCase createProductUseCase;

	@Mock
	private IProductoRepository iProductoRepository;

	@BeforeEach
	void setUp() {
		createProductUseCase = new CreateProductUseCase(iProductoRepository);
	}

	@Test
	void create() {

		Producto productoInt = Producto.cerateProducto().nombre("laptop dell 14")
				.descripcion("laptop dell 32G RAM, 1TB de ROM, i7").codigo("LA-86514A").existencia(40)
				.precioDetal(new BigDecimal(1000)).precioMayor(new BigDecimal(1000)).build();

		Producto productoOut = Producto.cerateProducto().nombre("laptop dell 14")
				.descripcion("laptop dell 32G RAM, 1TB de ROM, i7").codigo("LA-86514A").existencia(40)
				.precioDetal(new BigDecimal(1000)).precioMayor(new BigDecimal(1000)).id("6581e53baef6f725f4c42800")
				.build();

		when(iProductoRepository.save(productoInt)).thenReturn(Mono.just(productoOut));

		var result = createProductUseCase.create(productoInt);

		StepVerifier.create(result).assertNext(p -> {
			assertNotNull(p.getId());
			assertEquals(p.getPrecioDetal(), new BigDecimal(1000));
		}).verifyComplete();

		Mockito.verify(iProductoRepository).save(productoInt);
	}

}

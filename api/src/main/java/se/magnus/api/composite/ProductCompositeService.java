package se.magnus.api.composite;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "ProductComposite", description = "REST API for composite product information.")
public interface ProductCompositeService {

    @GetMapping(
            value = "/product-composite/{productId}",
            produces = "application/json"
    )
    ProductAggregate getProduct(@PathVariable int productId);
}

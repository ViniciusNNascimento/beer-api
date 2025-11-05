package one.digitalinnovation.beerstock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.dto.QuantityDTO;
import one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.exception.BeerStockExceededException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Beer Stock Management", description = "Manages beer stock operations")
public interface BeerControllerDocs {

    @Operation(summary = "Create a new beer", description = "Creates a new beer in the stock system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beer successfully created",
                    content = @Content(schema = @Schema(implementation = BeerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Missing required fields or wrong field range value",
                    content = @Content)
    })
    BeerDTO createBeer(@Valid @RequestBody BeerDTO beerDTO) throws BeerAlreadyRegisteredException;

    @Operation(summary = "Find beer by name", description = "Returns a beer found by the given name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beer successfully found",
                    content = @Content(schema = @Schema(implementation = BeerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Beer with given name not found",
                    content = @Content)
    })
    BeerDTO findByName(@PathVariable String name) throws BeerNotFoundException;

    @Operation(summary = "List all beers", description = "Returns a list of all beers registered in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all beers",
                    content = @Content(schema = @Schema(implementation = BeerDTO.class)))
    })
    List<BeerDTO> listBeers();

    @Operation(summary = "Delete beer by ID", description = "Deletes a beer found by the given valid ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beer successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Beer with given ID not found",
                    content = @Content)
    })
    void deleteById(@PathVariable Long id) throws BeerNotFoundException;
}
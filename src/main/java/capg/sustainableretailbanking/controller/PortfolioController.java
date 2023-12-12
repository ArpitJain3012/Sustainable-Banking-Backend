package capg.sustainableretailbanking.controller;

import capg.sustainableretailbanking.model.PortfolioModel;
import capg.sustainableretailbanking.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/portfolio")
@CrossOrigin("*")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getList() {
        try {
            List<PortfolioModel> portfolio = portfolioService.getList();
            if (portfolio.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }  return new ResponseEntity<>(portfolio, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getListById(@PathVariable int id) {
        try {
            PortfolioModel list = portfolioService.getListById(id);
            if (list != null) {
                return new ResponseEntity<>(list, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Portfolio not found");
                response.put("message", "Portfolio with ID " + id + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createList(@RequestBody List<PortfolioModel> list) {
        try {
            List<PortfolioModel> createdList = portfolioService.createList(list);
            return new ResponseEntity<>(createdList, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteList(@PathVariable int id) {
        try {
            boolean deleted = portfolioService.deleteList(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Portfolio not found");
                response.put("message", "Portfolio with ID " + id + " not found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/percentages")
    public ResponseEntity<Object> getCategoryPercentages() {
        try {
            Map<String, Double> categoryPercentages = portfolioService.getPercentageAmountSpentInAllCategories();

            if (categoryPercentages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categoryPercentages, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Internal server error");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

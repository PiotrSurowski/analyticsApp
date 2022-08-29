package com.surowski.analitycsapp.Controllers;

import com.surowski.analitycsapp.NeuralNetwork.NNService;
import com.surowski.analitycsapp.NeuralNetwork.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/network")
public class ChartController {

    @GetMapping("/get")
    public ResponseEntity<List<Result>> getClasificationResult(){
        NNService service = new NNService();
        return new ResponseEntity<>(service.clasify(), HttpStatus.OK);
    }

    @GetMapping("/train")
    public ResponseEntity<List<Double>> getTrainingErrors(){
        NNService service = new NNService();
        return new ResponseEntity<>(service.train(50000), HttpStatus.OK);
    }
}

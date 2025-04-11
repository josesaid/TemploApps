package com.bezkoder.spring.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.webflux.model.Tutorial;
import com.bezkoder.spring.webflux.service.TutorialService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author josesaidolanogarcia
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {
  @Autowired
  TutorialService tutorialService;
  
  @GetMapping("/tutorials")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
    if (title == null)
      return tutorialService.findAll();
    else
      return tutorialService.findByTitleContaining(title);
  }

  @GetMapping("/tutorials/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Tutorial> getTutorialById(@PathVariable("id") int id) {
    return tutorialService.findById(id);
  }

  @PostMapping("/tutorials")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    // La forma más común para observar cómo funciona la ejecución asíncrona consiste
    // en añadir operadores logging, utilizando `log()`:
    return tutorialService.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false))
            .log();  // Se añade log para visualizar el flujo

    //------------------
    /*
    Mono<Tutorial> result = tutorialService.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));

    result.subscribe(data -> System.out.println("Elemento recibido: " + data),
            error -> System.err.println("Error recibido: " + error),
            () -> System.out.println("Flujo completado"));

    return result;
    */

    //------------------
    /*
    return tutorialService.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false))
            .doOnSubscribe(subscription -> System.out.println("Suscrito en thread: " + Thread.currentThread().getName()))
            .doOnNext(tutorialResult -> System.out.println("Resultado en thread: " + Thread.currentThread().getName()));
    */
  }

  @PutMapping("/tutorials/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<Tutorial> updateTutorial(@PathVariable("id") int id, @RequestBody Tutorial tutorial) {
    return tutorialService.update(id, tutorial);
  }

  @DeleteMapping("/tutorials/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteTutorial(@PathVariable("id") int id) {
    return tutorialService.deleteById(id);
  }

  @DeleteMapping("/tutorials")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> deleteAllTutorials() {
    return tutorialService.deleteAll();
  }

  @GetMapping("/tutorials/published")
  @ResponseStatus(HttpStatus.OK)
  public Flux<Tutorial> findByPublished() {
    return tutorialService.findByPublished(true);
  }
}

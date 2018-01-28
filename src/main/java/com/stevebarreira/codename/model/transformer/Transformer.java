package com.stevebarreira.codename.model.transformer;

import java.util.List;
import java.util.stream.Collectors;

public interface Transformer<I,O> {

    O transform(I input);

     default List<O> transform(List<I> input){
         return input.stream().map(this::transform).collect(Collectors.toList());
     }
}

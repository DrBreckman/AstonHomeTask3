package ru.sedov.task3.mapper;

import java.util.List;

public interface EntityMapper<S, D> {
    D sourceToDestination(S source);
    S destinationToSource(D destinations);

    List<S> destinationToSource(List<D> destinations);
    List<D> sourceToDestination(List<S> source);
}

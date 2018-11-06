package com.program.backend.services;

import com.program.backend.beans.entity.ValueStream;
import com.program.backend.beans.response.valuestream.ValueStreamResponse;
import com.program.backend.repositories.ValueStreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValueStreamService {

    @Autowired
    private ValueStreamRepository valueStreamRepository;

    public Iterable<ValueStream> getAllValueStreams() {
        return valueStreamRepository.findAll();
    }

    public Iterable<ValueStreamResponse> getAllValueStreamEntityResponseList() {
        return ((List<ValueStream>) getAllValueStreams()).stream().map(ValueStreamResponse::new)
                .collect(Collectors.toList());
    }

    public ValueStream getValueStreamById(@NotNull Long id) {
        return valueStreamRepository.findValueStreamById(id).orElseThrow(RuntimeException::new);
    }
}

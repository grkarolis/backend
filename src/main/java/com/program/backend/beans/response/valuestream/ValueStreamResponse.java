package com.program.backend.beans.response.valuestream;

import com.program.backend.beans.entity.ValueStream;
import com.program.backend.beans.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class ValueStreamResponse extends Response {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    public ValueStreamResponse(ValueStream valueStream) {
        setId(valueStream.getId());
        setName(valueStream.getName());
    }
}

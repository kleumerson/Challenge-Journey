package com.journey.service.destination;

import com.journey.dto.destination.DestinationDto;
import com.journey.exceptions.destination.DestinationNotFoundException;
import com.journey.model.destination.Destination;
import com.journey.repository.destination.DestinationRepository;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinationService {
    private final static String OPENAI_TOKEN = "please, inform your token";
    @Autowired
    DestinationRepository destinationRepository;

    @Autowired
    ModelMapper modelMapper;
    public DestinationDto createDestination(DestinationDto destinationDto) throws Exception{
        Destination destination = modelMapper.map(destinationDto, Destination.class);

        Optional<Destination> optionalDestination = destinationRepository.findByName(destination.getName());
        if (optionalDestination.isPresent())
            throw new DestinationNotFoundException();

        if (destination.getDescription() == "" || destination.getDescription() == null) {
            CompletionChoice messageSearchChat = getConnectWithChatGPT(destination.getName());
            destination.setDescription(String.valueOf(messageSearchChat));
        }

        return modelMapper
                .map(destinationRepository.save(destination), DestinationDto.class);
    }

    public void deleteDestination(int id) {
        Optional<Destination> optionalDestination = destinationRepository.findById(id);
        if (optionalDestination.isPresent()) {
            destinationRepository.deleteById(id);
        } else {
            throw new DestinationNotFoundException();
        }
    }

    public DestinationDto updateDestination(DestinationDto destinationDto, int id) {
        Destination destination = modelMapper.map(destinationDto, Destination.class);
        destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException());
        destination.setId(id);
        destinationRepository.save(destination);
        return modelMapper
                .map(destination, DestinationDto.class);

    }

    public List<DestinationDto> getDestination() {
        return destinationRepository.findAll()
                .stream().map(m -> modelMapper.map(m, DestinationDto.class))
                .collect(Collectors.toList());
    }

    public List<DestinationDto> getNameDestination(String name) {
        List<Destination> destinations = destinationRepository.findByDestination(name);
        if (destinations.size() == 0) {
            throw new DestinationNotFoundException();
        }
        return destinations.stream()
                .map(m -> modelMapper.map(m, DestinationDto.class))
                .collect(Collectors.toList());
    }

    public DestinationDto getdetailsDestinations(int id) {
        Destination destination = destinationRepository.findByDestinationIdAll(id);
        return modelMapper.map(destination, DestinationDto.class);
    }

    public CompletionChoice getConnectWithChatGPT(String country) {
        OpenAiService service = new OpenAiService(OPENAI_TOKEN);

        StringBuilder sb = new StringBuilder();
        sb.append("Faça um resumo sobre ");
        sb.append(country);
        sb.append("enfatizando o porque este lugar é incrível. Utilize uma linguagem informal e até 100 caracteres no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.");

        CompletionRequest request = CompletionRequest.builder()
                .prompt(sb.toString())
                .model("text-davinci-003")
                .maxTokens(100)
                .build();

        return service.createCompletion(request).getChoices().get(0);
    }
}

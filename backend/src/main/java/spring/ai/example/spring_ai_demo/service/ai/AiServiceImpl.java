package spring.ai.example.spring_ai_demo.service.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Service;
import spring.ai.example.spring_ai_demo.controller.HiveController;
import spring.ai.example.spring_ai_demo.model.hive.Hive;
import spring.ai.example.spring_ai_demo.model.hive.HiveMaterial;
import spring.ai.example.spring_ai_demo.model.hive.HiveType;
import spring.ai.example.spring_ai_demo.service.hive.HiveService;

import java.util.List;

import static spring.ai.example.spring_ai_demo.model.hive.HiveMaterial.*;
import static spring.ai.example.spring_ai_demo.model.hive.HiveType.*;

@Service
public class AiServiceImpl implements AiService {
    private final ChatClient chatClient;
    private final HiveService hiveService;
    private final List<String> instructionKeyWords = List.of("GET_ALL_HIVES", "GET_HIVE", "NEW_HIVE", "DELETE_HIVE");
    private static final Logger log = LoggerFactory.getLogger(HiveController.class);

    public AiServiceImpl(ChatClient.Builder chatClientBuilder, HiveService hiveService) {
        this.chatClient = chatClientBuilder.build();
        this.hiveService = hiveService;
    }

    public String generate(String userInput) {
        log.info("Input from user: {}", userInput);
        if(userInput.isBlank() || userInput.length() <= 10) {
            return "Your question is too short";
        }

        String response = chatClient.prompt(getPrompt(userInput)).call().content();
        log.info("Response from llm: {}", response);

        List<String> matchedKeywords = instructionKeyWords.stream()
                .filter(response::contains)
                .toList();
        log.info("MatchedKeywords are: {}", matchedKeywords);

        if (!matchedKeywords.isEmpty()) {
            String cleanResponse = response.replace("\n", "").replace("\\n", "").replace("\"", "").replace("\'", "")
                    .replace(".", "").replace(" ", "");
            log.info("CleanResponse is: {}", cleanResponse);

            switch (matchedKeywords.get(0)) {
                case("GET_ALL_HIVES"):
                    return hiveService.getAllHives().toString();

                case("GET_HIVE"):
                    String hiveIdToGet = cleanResponse.split(",")[1];
                    return hiveService.getHiveById(Integer.parseInt(hiveIdToGet)).toString();

                case("NEW_HIVE"):
                    String hiveName = cleanResponse.split(",")[1];
                    HiveMaterial hiveMaterial = parseHiveMaterial(cleanResponse.split(",")[2]);
                    HiveType hiveType = parseHiveType(cleanResponse.split(",")[3]);
                    int framesPerBody = Integer.parseInt(cleanResponse.split(",")[4]);
                    return hiveService.addHive(new Hive(hiveName, hiveMaterial, hiveType, framesPerBody)).toString();

                case("DELETE_HIVE"):
                    String hiveIdToDelete = cleanResponse.split(",")[1];
                    return hiveService.deleteHive(Integer.parseInt(hiveIdToDelete));
                }
            }
        return response.replace("\\n", "").replace("\\ln", "").replace("\\", "");
        }

    private static Prompt getPrompt(String userInput) {
        String systemText = """
  By general questions from use:
  You are a helpful AI assistant named BeeAI and you should answer to user his questions about beekeeping and everything that is referring to beekeeping.
  You should short reject users questions when they do not refer to the topic of beekeeping or bees.
  Do not offer him extra rule.
  All the rules above should be used for all languages.
  
  Extra rule when the user asks (do not offer it him) to get, add or delete Hives (you can use only one of this templates):
  If user wants to see all hives than form your answer as follows (and nothing else): "GET_ALL_HIVES"
  If user wants to see a hive and gives you an ID-number than form your answer as follows (and nothing else): "GET_HIVE,0"
  If user wants to add new hive and gives parameters for it than form your answer as follows (and nothing else): "NEW_HIVE,hiveName,hiveMaterial,hiveType,framesPerBody"
  If user wants to delete a hive and gives you an ID-number than form your answer as follows (and nothing else): "DELETE_HIVE,0"
  """;
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage();

        Message userMessage = new UserMessage(userInput);
        return new Prompt(List.of(userMessage, systemMessage));
    }

    private HiveMaterial parseHiveMaterial(String hiveMaterialString) {
        return switch (hiveMaterialString.toUpperCase()) {
            case "WOOD" -> WOOD;
            case "PLASTIC" -> PLASTIC;
            case "POLYSTYRENE" -> POLYSTYRENE;
            case "POLYURETHANE" -> POLYURETHANE;
            case "METAL" -> METAL;
            case "STAINLESS_STEEL" -> STAINLESS_STEEL;
            case "ALUMINUM" -> ALUMINUM;
            case "CERAMIC" -> CERAMIC;
            case "BAMBOO" -> BAMBOO;
            case "STRAW" -> STRAW;
            case "GRASS" -> GRASS;
            case "REED" -> REED;
            case "CARDBOARD" -> CARDBOARD;
            case "HARD_FOAM" -> HARD_FOAM;
            case "FIBERGLASS" -> FIBERGLASS;
            case "CEMENT" -> CEMENT;
            case "CONCRETE" -> CONCRETE;
            case "PVC" -> PVC;
            case "GLASS" -> GLASS;
            case "COMPOSITE_MATERIALS" -> COMPOSITE_MATERIALS;
            case "RECYCLED_MATERIALS" -> RECYCLED_MATERIALS;
            default -> UNKNOWN_HIVE_MATERIAL;
        };
    }


    private HiveType parseHiveType(String hiveTypeString) {
        return switch (hiveTypeString.toUpperCase()) {
            case "DADANT" -> DADANT;
            case "LANGSTROTH" -> LANGSTROTH;
            case "LAYENS" -> LAYENS;
            case "WARRÉ", "WARRE" -> WARRÉ;
            case "TOP_BAR" -> TOP_BAR;
            case "KENYAN_TOP_BAR_HIVE", "KENYAN" -> KENYAN_TOP_BAR_HIVE;
            case "FLOW_HIVE", "FLOW" -> FLOW_HIVE;
            case "AZ_HIVE" -> AZ_HIVE;
            case "ZANDER" -> ZANDER;
            case "ALPINE_HIVE" -> ALPINE_HIVE;
            case "DANDANT_BLATT" -> DANDANT_BLATT;
            case "UKRAINIAN_HIVE" -> UKRAINIAN_HIVE;
            case "RUSSIAN_HIVE" -> RUSSIAN_HIVE;
            case "JUMBO" -> JUMBO;
            case "MINI_PLUS" -> MINI_PLUS;
            case "NUC_BOX", "NUC" -> NUC_BOX;
            case "SQUARE_HIVE" -> SQUARE_HIVE;
            case "ROUNDED_BASKET_HIVE" -> ROUNDED_BASKET_HIVE;
            case "SKERP" -> SKERP;
            case "SKEP" -> SKEP;
            case "LOG_HIVE" -> LOG_HIVE;
            case "TREE_HIVE" -> TREE_HIVE;
            case "TROUGH_HIVE" -> TROUGH_HIVE;
            case "PERONE_HIVE" -> PERONE_HIVE;
            case "HORIZONTAL_HIVE" -> HORIZONTAL_HIVE;
            case "VERTICAL_HIVE" -> VERTICAL_HIVE;
            case "LONG_LANGSTROTH" -> LONG_LANGSTROTH;
            case "INSULATED_HIVE" -> INSULATED_HIVE;
            case "EINAR_HIVE" -> EINAR_HIVE;
            case "WBC_HIVE" -> WBC_HIVE;
            case "COMMERCIAL_HIVE" -> COMMERCIAL_HIVE;
            case "NATIONAL_HIVE" -> NATIONAL_HIVE;
            case "SMALL_CELL_HIVE" -> SMALL_CELL_HIVE;
            case "TANZANIAN_TOP_BAR_HIVE" -> TANZANIAN_TOP_BAR_HIVE;
            case "GREEK_HIVE" -> GREEK_HIVE;
            case "TURKISH_HIVE" -> TURKISH_HIVE;
            case "GERMAN_NORMAL_HIVE" -> GERMAN_NORMAL_HIVE;
            case "MODIFIED_DADANT" -> MODIFIED_DADANT;
            case "CARNICA_HIVE" -> CARNICA_HIVE;
            default -> UNKNOWN_HIVE_TYPE;
        };
    }
}

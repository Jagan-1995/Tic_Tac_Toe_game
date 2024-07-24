package TIcTacToe.factory;

import TIcTacToe.models.BotDifficultyLevel;
import TIcTacToe.strategies.botPlayingStrategies.BotPlayingStrategy;
import TIcTacToe.strategies.botPlayingStrategies.EasyBotPlayingStrategy;
import TIcTacToe.strategies.botPlayingStrategies.HardBotPlayingStrategy;
import TIcTacToe.strategies.botPlayingStrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel){
        if (botDifficultyLevel.equals(BotDifficultyLevel.EASY)){
            return new EasyBotPlayingStrategy();
        } else if (botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        } else if (botDifficultyLevel.equals(BotDifficultyLevel.HARD)) {
            return new HardBotPlayingStrategy();
        }

        return null;
    }
}

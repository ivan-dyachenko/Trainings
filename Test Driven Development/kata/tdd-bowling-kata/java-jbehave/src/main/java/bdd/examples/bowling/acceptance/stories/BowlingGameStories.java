package bdd.examples.bowling.acceptance.stories;

import bdd.examples.bowling.acceptance.steps.BowlingGameSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

public class BowlingGameStories extends JUnitStories {

    public BowlingGameStories() {
        addSteps(new BowlingGameSteps());
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryReporterBuilder(new StoryReporterBuilder()
                                                 .withDefaultFormats()
                                                 .withFormats(Format.IDE_CONSOLE));
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(),
                                           asList("**/" + System.getProperty("storyFilter", "*") + ".story"), null);
    }
}

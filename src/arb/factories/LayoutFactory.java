package arb.factories;

import arb.core.ViewController;
import arb.view.HelpView;
import arb.view.LogView;
import arb.view.MainApplicationView;
import arb.view.RootStackPane;
import arb.view.SearchView;
import arb.view.SettingsView;
import arb.view.SideBarView;
import arb.view.character.BaseGameCharacterConfigView;
import arb.view.character.CharacterConfigTabPane;
import arb.view.character.OrdinatorCharacterConfigView;
import arb.view.character.SharedCharacterConfigView;
import arb.view.control.LabeledComboBox;
import arb.view.control.LabeledTextField;
import arb.view.effects.EffectsActionButtons;
import arb.view.effects.EffectsView;
import arb.view.filter.FilterPotionByTypeRadioButtons;
import arb.view.filter.FilterPotionsActionButtons;
import arb.view.filter.FilterPotionsView;
import arb.view.filter.GameExtensionCheckBoxes;
import arb.view.result.ResultTableView;
import arb.view.util.ResourcePathConstants;
import arb.view.util.ShadowRectangle;
import arb.view.util.WindowHelper;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class contains methods to instantiate all views shown in the
 * application.
 */
public class LayoutFactory {

	private static final int HELP_STAGE_HEIGHT = 600;

	private static final int HELP_STAGE_WIDTH = 700;

	private static LayoutFactory layoutFactoryInstance;

	public static LayoutFactory getInstance() {
		if (layoutFactoryInstance == null) {
			layoutFactoryInstance = new LayoutFactory();
		}
		return layoutFactoryInstance;
	}

	public RootStackPane createRootStackPane(Region applicationView) {
		return new RootStackPane(applicationView);
	}

	public MainApplicationView createMainApplicationView() {
		return new MainApplicationView();
	}

	public SideBarView createSideBarView() {
		return new SideBarView();
	}

	public SettingsView createSettingsView() {
		return new SettingsView();
	}

	public LogView createLogView() {
		final LogView logView = new LogView();
		ViewController.getInstance().setLogView(logView);
		return logView;
	}

	public CharacterConfigTabPane createCharacterConfigView() {
		return new CharacterConfigTabPane();
	}

	public SearchView createSearchView() {
		return new SearchView();
	}

	public EffectsView createEffectsView() {
		final EffectsView effectsView = new EffectsView();
		ViewController.getInstance().setEffectsView(effectsView);
		return effectsView;
	}

	public EffectsActionButtons createEffectsActionButtons() {
		final EffectsActionButtons effectsActionButtons = new EffectsActionButtons();
		ViewController.getInstance().setEffectsActionButtons(effectsActionButtons);
		return effectsActionButtons;
	}

	public FilterPotionsView createFilterPotionsView() {
		final FilterPotionsView filterIngredientsView = new FilterPotionsView();
		ViewController.getInstance().setFilterPotionsView(filterIngredientsView);
		return filterIngredientsView;
	}

	public FilterPotionByTypeRadioButtons createFilterPotionByTypeRadioButtons() {
		final FilterPotionByTypeRadioButtons filterPotionByTypeRadioButtons = new FilterPotionByTypeRadioButtons();
		ViewController.getInstance()
				.setFilterPotionByTypeRadioButtons(filterPotionByTypeRadioButtons);
		return filterPotionByTypeRadioButtons;
	}

	public FilterPotionsActionButtons createFilterIngredientsActionButtons() {
		final FilterPotionsActionButtons filterIngredientsActionButtons = new FilterPotionsActionButtons();
		ViewController.getInstance()
				.setFilterPotionsActionButtons(filterIngredientsActionButtons);
		return filterIngredientsActionButtons;
	}

	public GameExtensionCheckBoxes createGameExtensionCheckBoxes() {
		final GameExtensionCheckBoxes gameExtensionCheckBoxes = new GameExtensionCheckBoxes();
		ViewController.getInstance().setGameExtensionCheckBoxes(gameExtensionCheckBoxes);
		return gameExtensionCheckBoxes;
	}

	public ResultTableView createResultTableView() {
		final ResultTableView resultTableView = new ResultTableView();
		ViewController.getInstance().setResultsTableView(resultTableView);
		return resultTableView;
	}

	public SharedCharacterConfigView createSharedCharacterConfigView() {
		final SharedCharacterConfigView sharedCharacterConfigView = new SharedCharacterConfigView();
		ViewController.getInstance()
				.setSharedCharacterConfigView(sharedCharacterConfigView);
		return sharedCharacterConfigView;
	}

	public BaseGameCharacterConfigView createBaseGameCharacterConfigView() {
		final BaseGameCharacterConfigView baseGameCharacterConfigView = new BaseGameCharacterConfigView();
		ViewController.getInstance()
				.setBaseGameCharacterConfigView(baseGameCharacterConfigView);
		return baseGameCharacterConfigView;
	}

	public OrdinatorCharacterConfigView createOrdinatorCharacterConfigView() {
		final OrdinatorCharacterConfigView ordinatorCharacterConfigView = new OrdinatorCharacterConfigView();
		ViewController.getInstance()
				.setOrdinatorCharacterConfigView(ordinatorCharacterConfigView);
		return ordinatorCharacterConfigView;
	}

	public LabeledTextField createLabeledTextField() {
		return new LabeledTextField();
	}

	public LabeledComboBox createLabeledComboBox() {
		return new LabeledComboBox();
	}

	public ShadowRectangle createShadowRectangle() {
		return new ShadowRectangle();
	}

	public void showHelpView() {
		final Stage helpStage = ViewController.getInstance().getHelpStage();
		// Don't create a second instance if one is already available.
		if (helpStage != null) {
			helpStage.toFront();
		} else {
			this.createNewHelpView();
		}
	}

	/** Creates a new stage, scene, and then a HelpView, which is nested inside. */
	private void createNewHelpView() {
		final Stage helpStage = this.createHelpStage();
		final HelpView helpView = new HelpView(helpStage);
		final RootStackPane rootStackPane = LayoutFactory.getInstance()
				.createRootStackPane(helpView);
		final Scene helpScene = new Scene(rootStackPane, HELP_STAGE_WIDTH,
				HELP_STAGE_HEIGHT);
		helpScene.getStylesheets().add(ResourcePathConstants.APPLICATION_CSS);
		helpScene.setFill(Color.TRANSPARENT);
		helpStage.setScene(helpScene);
		WindowHelper.addResizeAndDragListener(helpStage, helpView);
		helpStage.show();
	}

	private Stage createHelpStage() {
		final Stage helpStage = new Stage();
		helpStage.initStyle(StageStyle.TRANSPARENT);
		helpStage.centerOnScreen();
		helpStage.setMinHeight(600);
		ViewController.getInstance().setHelpStage(helpStage);
		return helpStage;
	}

	private LayoutFactory() {
		// Private constructor to prevent external instantiation.
	}

}

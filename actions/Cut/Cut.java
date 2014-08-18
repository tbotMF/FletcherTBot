package scripts.actions.Cut;

import org.tribot.api.General;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;

import scripts.Globals;
import scripts.sbf.action.Action;
import scripts.sbf.skill.SkillGlobals;
import scripts.sbf.util.ABC;

public class Cut extends Action {
	private final String itemToUse = selections.get("ItemToUse");
	private final String product = selections.get("Product");
	private final ABC abc = manager.getABC();


	private void performAntiBan() {
		this.abc.doAllIdleActions(SKILLS.FLETCHING, GameTab.TABS.INVENTORY);
		this.abc.hoverNextObject("Bank");
	}

	private boolean updateFletchingLevel() {
		if (Skills.getCurrentLevel(SKILLS.FLETCHING) > skillManager
				.getCurrentLevel()) {
			skillManager.updateCurrentLevel();
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		print("Cutting " + itemToUse.toLowerCase() + " to make "
				+ itemToUse.toLowerCase().split(" ")[0] + " "
				+ product.toLowerCase() + "s.");
		
		while (Globals.HAS_RESOURCES.getStatus()) {
			General.sleep(100, 200);
			performAntiBan();
			if (updateFletchingLevel())
				break;

		}
		SkillGlobals.SKILLING.setStatus(Globals.HAS_RESOURCES.getStatus());
	}

	@Override
	public boolean isValid() {

		return Globals.CUT.getStatus() && Player.getAnimation() > -1;
	}

}

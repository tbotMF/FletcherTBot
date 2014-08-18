package scripts.actions.Make;

import org.tribot.api.General;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Skills;
import org.tribot.api2007.Skills.SKILLS;
import org.tribot.api2007.types.RSInterface;

import scripts.Globals;
import scripts.sbf.action.Action;
import scripts.sbf.skill.SkillGlobals;
import scripts.sbf.util.ABC;

public class Make10Sets extends Action {

	private final String product = selections.get("Product");
	private final String itemToUse = selections.get("ItemToUse");
	private final ABC abc = manager.getABC();
	private void performAntiBan() {
		this.abc.doAllIdleActions(SKILLS.FLETCHING, GameTab.TABS.INVENTORY);
		this.abc.hoverNextItem(itemToUse);
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
		final int productCountInitial = Inventory.getCount(product);
		RSInterface makeProduct = skillManager.getChildInterfaceFor(
				Interfaces.get(582), product);

		if (makeProduct == null)
			return;
		if (!makeProduct.click("Make 10 sets"))
			return;
		
		while (productCountInitial + 150 != Inventory.getCount(product)
				&& Globals.HAS_RESOURCES.getStatus()) {
			General.sleep(100, 200);
			performAntiBan();
			if (updateFletchingLevel())
				break;

		}
		if (!Globals.HAS_RESOURCES.getStatus())
			this.abc.getABCUtil().BOOL_TRACKER.HOVER_NEXT.reset();

		SkillGlobals.SKILLING.setStatus(Globals.HAS_RESOURCES.getStatus());

	}

	@Override
	public boolean isValid() {
		return Globals.CLICK_TEN_SETS.getStatus();
	}

}

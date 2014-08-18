package scripts.actions;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;

import scripts.Globals;
import scripts.sbf.action.Action;
import scripts.sbf.util.ABC;

public class UseItemWithAnother extends Action {
	private final ABC abc = manager.getABC();
	private final String[] resources = skillManager.getInventoryResources();

	@Override
	public void execute() {
		for (String resource : resources)
			if (Inventory.find(resource)[0].click("Use"))
				this.abc.waitItemInteractionDelay();
		if (!Globals.MAKE_UNF.getStatus())
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					General.sleep(100, 200);
					return Interfaces.get(skillManager.getMasterIndex()) != null;
				}

			}, General.random(3000, 4000));

	}

	@Override
	public boolean isValid() {

		return Globals.HAS_RESOURCES.getStatus();
	}

}

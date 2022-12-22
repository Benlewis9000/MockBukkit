package be.seeseemelk.mockbukkit.inventory;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

/**
 * Mock implementation of a Chest {@link InventoryType}.
 *
 * @see InventoryMock
 * @see InventoryType#CHEST
 */
public class ChestInventoryMock extends InventoryMock
{

	/**
	 * Constructs a new {@link ChestInventoryMock} for the given holder.
	 *
	 * @param holder The holder of the inventory.
	 * @param size   The size of the inventory.
	 * @see InventoryMock#InventoryMock(InventoryHolder, int, InventoryType)
	 */
	public ChestInventoryMock(InventoryHolder holder, int size)
	{
		super(holder, size, InventoryType.CHEST);
	}

	@Override
	@NotNull
	public Inventory getSnapshot()
	{
		Inventory inventory = new ChestInventoryMock(getHolder(), getSize());
		inventory.setContents(getContents());
		return inventory;
	}

}

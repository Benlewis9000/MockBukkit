package be.seeseemelk.mockbukkit.block.state;

import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import be.seeseemelk.mockbukkit.UnimplementedOperationException;
import be.seeseemelk.mockbukkit.block.BlockMock;
import be.seeseemelk.mockbukkit.metadata.MetadataTable;

@SuppressWarnings("deprecation")
public class BlockStateMock implements BlockState, Cloneable
{

	private final MetadataTable metadataTable = new MetadataTable();
	private Block block;
	private Material material;

	public BlockStateMock(@NotNull Material material)
	{
		this.material = material;
	}

	public BlockStateMock(@NotNull Block block)
	{
		this.block = block;
		this.material = block.getType();
	}

	private BlockStateMock(@NotNull BlockStateMock state)
	{
		this.material = state.getType();
		this.block = state.isPlaced() ? state.getBlock() : null;
	}

	@Override
	public void setMetadata(String metadataKey, MetadataValue newMetadataValue)
	{
		metadataTable.setMetadata(metadataKey, newMetadataValue);
	}

	@Override
	public List<MetadataValue> getMetadata(String metadataKey)
	{
		return metadataTable.getMetadata(metadataKey);
	}

	@Override
	public boolean hasMetadata(String metadataKey)
	{
		return metadataTable.hasMetadata(metadataKey);
	}

	@Override
	public void removeMetadata(String metadataKey, Plugin owningPlugin)
	{
		metadataTable.removeMetadata(metadataKey, owningPlugin);
	}

	@Override
	public Block getBlock()
	{
		if (block == null)
		{
			throw new IllegalStateException("This BlockState has not been placed!");
		}
		else
		{
			return block;
		}
	}

	@Override
	@Deprecated
	public MaterialData getData()
	{
		return new MaterialData(material);
	}

	@Override
	public Material getType()
	{
		return material;
	}

	@Override
	public byte getLightLevel()
	{
		return getBlock().getLightLevel();
	}

	@Override
	public World getWorld()
	{
		return getBlock().getWorld();
	}

	@Override
	public int getX()
	{
		return getBlock().getX();
	}

	@Override
	public int getY()
	{
		return getBlock().getY();
	}

	@Override
	public int getZ()
	{
		return getBlock().getZ();
	}

	@Override
	public Location getLocation()
	{
		return getBlock().getLocation();
	}

	@Override
	public Location getLocation(Location loc)
	{
		return getBlock().getLocation(loc);
	}

	@Override
	public Chunk getChunk()
	{
		return getBlock().getChunk();
	}

	@Override
	@Deprecated
	public void setData(@NotNull MaterialData data)
	{
		this.material = data.getItemType();
	}

	@Override
	public void setType(Material type)
	{
		this.material = type;
	}

	@Override
	public boolean update()
	{
		return update(false);
	}

	@Override
	public boolean update(boolean force)
	{
		return update(force, true);
	}

	@Override
	public boolean update(boolean force, boolean applyPhysics)
	{
		Block block = getBlock();

		if (block instanceof BlockMock && (force || block.getType() == material))
		{
			((BlockMock) block).setState(this);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	@Deprecated
	public byte getRawData()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	@Deprecated
	public void setRawData(byte data)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public boolean isPlaced()
	{
		return block != null;
	}

	@Override
	public BlockData getBlockData()
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	@Override
	public void setBlockData(BlockData data)
	{
		// TODO Auto-generated method stub
		throw new UnimplementedOperationException();
	}

	/**
	 * This returns a copy of this {@link BlockStateMock}.
	 * Inheritents of this class should override this method!
	 * 
	 * @return A snapshot of this {@link BlockStateMock}.
	 */
	public BlockState getSnapshot()
	{
		return new BlockStateMock(this);
	}
}

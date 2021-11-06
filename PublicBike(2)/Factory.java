package PublicBike;

public interface Factory <T extends Manageable> {
	public T create(int i);
}

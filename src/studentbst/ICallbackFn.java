package studentbst;

@FunctionalInterface
public interface ICallbackFn<T> {
    void callback(Node<T> cbNode);
}

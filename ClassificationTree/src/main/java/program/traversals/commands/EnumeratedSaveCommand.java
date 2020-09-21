package program.traversals.commands;

import program.Datum;
import program.structures.Node;
import program.structures.Tree;
import program.structures.impl.LinkedBinaryTree;
import java.io.PrintWriter;

/**
 * A simple Traversal Command for Datum Objects which writes to a PrintWriter
 * the necessary information load a tree from a file.
 *
 */
public class EnumeratedSaveCommand extends TraversalCommand<Datum> {

    private final PrintWriter writer;

    /**
     * Constructs a new EnumeratedSaveCommand for program.Datum objects which will use
     * the provided PrintWriter to write output to a file.
     *
     * @param writer The output print writer object.
     */
    public EnumeratedSaveCommand(PrintWriter writer) {
        this.writer = writer;
    }

    /**
     * The appropriate format for the output should be as follows: %d:%d:%s where:
     * <ul>
     *  <li>The first number is the number of the parent datum object, or -1 if the parent is null
     *  <li>The second number is the number associated with the current datum object
     *  <li>The string is the value of the prompt for the current datum object
     * </ul>
     * Also note that each datum object should occupy its own line in the file
     * {@inheritDoc}
     */
    @Override
    public void execute(Tree<Datum> tree, Node<Datum> node) {
        Datum data = node.getElement();
        int parentNum = node.getParent() == null ? -1 : node.getParent().getElement().getNumber();
        String side = "r";
        if (node.getParent() != null)
            side = node.equals(((LinkedBinaryTree) tree).left(node.getParent())) ? "l" : "r";
        writer.printf("%d:%d:%s:%s%n", parentNum, data.getNumber(), side, data.getPrompt());
        writer.flush();
    }

}

package dev.codewithfriends;

import java.util.List;
import java.util.LinkedList;
import java.lang.Integer;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BinarySearchTreeTest 
{

    BinarySearchTree<Integer> bst;

    @Before
    public void setUp() {
        bst = new BinarySearchTree<Integer>();
        bst.insert(6);
        bst.insert(2);
        bst.insert(8);
        bst.insert(1);
        bst.insert(3);
        bst.insert(4);
    }

    @Test
    public void testInsertAndContains()
    {
        assertTrue(bst.contains(6));
    }

    @Test
    public void testDFS()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(0);
        bst.insert(3);
        bst.insert(1);
        bst.insert(4);
        bst.insert(2);

        List<Integer> visited = BSTMain.dfs(bst.getRoot(), new LinkedList<>());
        assertEquals(5, visited.size());
        assertEquals(Integer.valueOf(0), visited.get(0));
        assertEquals(Integer.valueOf(3), visited.get(1));
        assertEquals(Integer.valueOf(1), visited.get(2));
        assertEquals(Integer.valueOf(2), visited.get(3));
        assertEquals(Integer.valueOf(4), visited.get(4));
    }

<<<<<<< HEAD
    @Test
=======
>>>>>>> 46ed45751fe022126556898b93f052add9f6aec3
    public void testDFSDiagram()
    {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(0);
        bst.insert(3);
        bst.insert(1);
        bst.insert(4);
        bst.insert(2);

        List<String> diagram = BSTMain.dfsDiagram(bst.getRoot(), new LinkedList<>());
        assertEquals(4, diagram.size());
        assertEquals("    0 --> 3", diagram.get(0));
        assertEquals("    3 --> 1", diagram.get(1));
        assertEquals("    1 --> 2", diagram.get(2));
        assertEquals("    3 --> 4", diagram.get(3));
        for (String d : diagram) {
            System.out.println(d);
        }
    }

<<<<<<< HEAD

=======
>>>>>>> 46ed45751fe022126556898b93f052add9f6aec3
}

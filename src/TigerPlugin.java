import static org.openstreetmap.josm.tools.I18n.tr;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.util.Collection;
import java.util.LinkedList;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import org.openstreetmap.josm.Main;
import org.openstreetmap.josm.plugins.Plugin;
import org.openstreetmap.josm.plugins.PluginInformation;
import org.openstreetmap.josm.actions.mapmode.MapMode;
import org.openstreetmap.josm.actions.JosmAction;
import org.openstreetmap.josm.command.ChangeCommand;
import org.openstreetmap.josm.command.Command;
import org.openstreetmap.josm.command.SequenceCommand;
import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.data.SelectionChangedListener;
import org.openstreetmap.josm.data.coor.*;
import org.openstreetmap.josm.data.osm.DataSet;
import org.openstreetmap.josm.data.osm.Node;
import org.openstreetmap.josm.data.osm.OsmPrimitive;
import org.openstreetmap.josm.data.osm.Way;
import org.openstreetmap.josm.tools.Shortcut;

public class TigerPlugin extends Plugin {
  public TigerPlugin(PluginInformation info) {
    super(info);
    registerAction();
  }

  private void registerAction() {
    JButton btn = new JButton(new RemoveTigerReviewedTagAction());
    btn.setText(null);
    btn.setBorder(BorderFactory.createEmptyBorder());
    btn.setPreferredSize(new Dimension(18, 18));
  }

  private static class RemoveTigerReviewedTagAction extends JosmAction {
    public RemoveTigerReviewedTagAction() {
      super(tr("Remove tiger:reviewed tag"), null,
            tr("Remove tiger:reviewed tag"),
            Shortcut.registerShortcut("plugin:tiger:dialog", tr("Remove tiger:reviewed tag"), KeyEvent.VK_R, Shortcut.SHIFT), false);
    }

    public void actionPerformed(ActionEvent e) {
      Collection<Way> selectedWays = Main.main.getCurrentDataSet().getSelectedWays();
      Collection<Command> cmds = new LinkedList<Command>();

      for (Way currentWay : selectedWays) {
        Way changedWay = new Way(currentWay);
        changedWay.remove("tiger:reviewed");
        cmds.add(new ChangeCommand(currentWay, changedWay));
      }

      Command removedTigerTag = new SequenceCommand(tr("Removed tiger:reviewed tag"), cmds);
      Main.main.undoRedo.add(removedTigerTag);
      Main.map.repaint();
    }
  }
}

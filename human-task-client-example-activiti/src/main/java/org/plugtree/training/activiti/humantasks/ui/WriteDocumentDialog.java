package org.plugtree.training.activiti.humantasks.ui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;


import org.activiti.engine.task.Task;
import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.plugtree.training.activiti.humantasks.core.HumanTaskClient;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class WriteDocumentDialog extends javax.swing.JDialog {
	private JTextArea writeDocumentPanel;
	private JLabel taskLabel;
	private JButton submitWriteButton;
	private Task task;
	private String user;
	private HumanTaskClient client;
	private long startTime;
	private JLabel referenceLabel;

	public WriteDocumentDialog(JFrame frame, Task task, String user,
			HumanTaskClient theClient, String reference) {
		super(frame);
		this.user = user;
		this.task = task;
		this.client = theClient;
		initGUI();
	}

	private void initGUI() {
		try {
			AnchorLayout thisLayout = new AnchorLayout();
			getContentPane().setLayout(thisLayout);
			{
				referenceLabel = new JLabel();
				getContentPane().add(
						referenceLabel,
						new AnchorConstraint(12, 703, 68, 503,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				referenceLabel
						.setPreferredSize(new java.awt.Dimension(136, 15));
				referenceLabel.setName("reference");
			}
			javax.swing.ActionMap actionMap = org.jdesktop.application.Application
					.getInstance(
							Application.class)
					.getContext().getActionMap(WriteDocumentDialog.class, this);
			{
				taskLabel = new JLabel();
				getContentPane().add(taskLabel, new AnchorConstraint(27, 394, 101, 11, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				taskLabel.setPreferredSize(new java.awt.Dimension(261, 20));
				taskLabel.setText("Your task is "
						+ task.getDescription());
			}
			{
				submitWriteButton = new JButton();
				getContentPane().add(
						submitWriteButton,
						new AnchorConstraint(772, 378, 929, 66,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				submitWriteButton.setPreferredSize(new java.awt.Dimension(120,
						41));
				submitWriteButton.setName("SubmitWriteButton");
				submitWriteButton.setAction(actionMap.get("completeTask"));
				submitWriteButton.setText("Submit Task");
			}
			{
				writeDocumentPanel = new JTextArea();
				getContentPane().add(
						writeDocumentPanel,
						new AnchorConstraint(101, 447, 727, 37,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL,
								AnchorConstraint.ANCHOR_REL));
				writeDocumentPanel.setPreferredSize(new java.awt.Dimension(279,
						169));
				writeDocumentPanel.setName("writeDocumentPanel");
			}
			this.setSize(492, 300);
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(getContentPane());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Action
	public void completeTask() {
		Map result = new HashMap();
		result.put("Result", this.writeDocumentPanel.getText());
		this.client.completeTask(user, this.task, result);
		this.setVisible(false);
	}

}

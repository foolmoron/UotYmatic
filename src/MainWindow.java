import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DebugGraphics;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.cloudgarden.layout.AnchorConstraint;
import com.cloudgarden.layout.AnchorLayout;

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
public class MainWindow extends javax.swing.JFrame {
	private JLabel urlLaber;
	private JTextField URLfield;
	private JButton fetchBtn;
	private JPanel votePanel;
	private JMenuBar mainMenu;
	private JLabel dLabel;
	private JLabel rLabel;
	private JLabel dtextLbl;
	private JLabel rtextLbl;
	private JScrollPane voteScrollPane;
	private JList numList;
	private JList posterList;
	private JButton replaceDialogApply;
	private JTextField replaceDialogTextField;
	private JLabel replaceDialogLabel;
	private JDialog replaceDialog;
	private JButton applyParamBtn;
	private JTextField votesperpostField;
	private JLabel paramLabel1;
	private JDialog voteParameterDialog;
	private JMenuItem fileVoteParameters;
	private JMenuItem editCorrect;
	private JLabel countLabel;
	private JButton applyCorrect;
	private JScrollPane correctScrollPane;
	private JTextArea correctArea;
	private JDialog correctDialog;
	private JButton correctBtn;
	private JMenuItem graphsVotals;
	private JScrollPane votalsScrollPane;
	private JTextArea votalsArea;
	private JDialog votalsDialog;
	private JCheckBox timeLimitChkBox;
	private JLabel about6;
	private JMenu helpMenu;
	private JMenuItem fileReload;
	private JLabel warningLabel;
	private JLabel about5;
	private JLabel about4;
	private JLabel about3;
	private JLabel about2;
	private JLabel about1;
	private JDialog aboutDialog;
	private JButton cancelBtn;
	private JButton closeBtn;
	private JLabel closeLabel;
	private JDialog closeDialog;
	private JMenuItem editUndo;
	private JLabel undoLabel;
	private JScrollPane editUserScrollPane;
	private JTextArea editUserArea;
	private JButton undoBtn;
	private JList voteList;
	private JSplitPane voteSplitPane2;
	private JSplitPane voteSplitPane1;
	private JButton voteSort;
	private JPanel sortPanel;
	private JSplitPane voteSplitVertical;
	private JMenuItem editDelete;
	private JMenuItem editReplace;
	private JLabel votesLabel;
	public JCheckBox allowMultipleChkBox;
	private JMenu graphsMenu;
	private JMenu editMenu;
	private JLabel sortLabel;
	private JButton ptsSort;
	private JButton numSort;
	private JSeparator sep1;
	private JPanel functionPanel;
	private JMenuItem fileAbout;
	private JLabel statusLabel;
	private JMenuItem fileClose;
	private JMenu fileMenu;
	private JButton deleteBtn;
	private JButton replaceBtn;
	private JLabel editUsersTip;
	private JButton applyEditBtn;
	private JPanel editUsersPanel;
	private JList userList;
	private JScrollPane userScrollPane;
	private JPanel userPanel;
	public JTabbedPane userTabbedPane;
	private String numSelection = "SELECT POST NUMBER(S)";
	private String posterSelection = "SELECT POSTER USERNAME(S)";
	private String voteSelection = "SELECT VOTE(S)";
	private String userSelection = "SELECT USER";
	private final String sla = "/";
	private final String spa = "";

	/**
	 * Auto-generated main method to display this JFrame
	 */
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				MainWindow inst = new MainWindow();
//				inst.setLocationRelativeTo(null);
//				inst.setVisible(true);
//			}
//		});
//	}

	public MainWindow() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GroupLayout thisLayout = new GroupLayout(
					(JComponent) getContentPane());
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			this.setTitle("UotYmatic by foolmoron");
			this.setMinimumSize(new java.awt.Dimension(450, 375));
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					thisWindowClosing(evt);
				}
			});
			{
				mainMenu = new JMenuBar();
				setJMenuBar(mainMenu);
				mainMenu.setSize(432, 20);
				mainMenu.setFont(new java.awt.Font("Segoe UI", 0, 11));
				{
					fileMenu = new JMenu();
					mainMenu.add(getFileMenu());
					fileMenu.setText("File");
				}
				{
					editMenu = new JMenu();
					mainMenu.add(editMenu);
					editMenu.setText("Edit");
					{
						editReplace = new JMenuItem();
						editMenu.add(getEditCorrect());
						editMenu.add(editReplace);
						editReplace.setText("Replace");
						editReplace.setAccelerator(KeyStroke
								.getKeyStroke("ctrl pressed R"));
						editReplace.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out
										.println("editReplace.actionPerformed, event="
												+ evt);
								replaceBtnActionPerformed(evt);
							}
						});
					}
					{
						editDelete = new JMenuItem();
						editMenu.add(editDelete);
						editMenu.add(getEditUndo());
						editDelete.setText("Delete");
						editDelete.setAccelerator(KeyStroke
								.getKeyStroke("ctrl pressed D"));
						editDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								System.out
										.println("editDelete.actionPerformed, event="
												+ evt);
								deleteBtnActionPerformed(evt);
							}
						});
					}
				}
				{
					graphsMenu = new JMenu();
					mainMenu.add(graphsMenu);
					mainMenu.add(getHelpMenu());
					graphsMenu.setText("Graphs");
					graphsMenu.add(getGraphsVotals());
				}
			}
			{
				urlLaber = new JLabel();
				urlLaber.setText("Topic URL :");
			}
			{
				rLabel = new JLabel();
				rLabel.setFont(new java.awt.Font("Arial Narrow", 0, 11));
				rLabel.setForeground(new java.awt.Color(0, 0, 255));
				rLabel.setText(voteSelection + " with " + userSelection);
			}
			{
				dLabel = new JLabel();
				dLabel.setFont(new java.awt.Font("Arial Narrow", 0, 11));
				dLabel.setForeground(new java.awt.Color(255, 0, 0));
				dLabel.setText(numSelection + " or " + posterSelection);
			}
			{
				rtextLbl = new JLabel();
				rtextLbl.setText("ctrl+r:");
				rtextLbl.setFont(new java.awt.Font("Arial Narrow", 0, 11));
				rtextLbl.setForeground(new java.awt.Color(0, 0, 255));
			}
			{
				dtextLbl = new JLabel();
				dtextLbl.setText("ctrl+d:");
				dtextLbl.setFont(new java.awt.Font("Arial Narrow", 0, 11));
				dtextLbl.setForeground(new java.awt.Color(255, 0, 0));
			}
			{
				allowMultipleChkBox = new JCheckBox();
				allowMultipleChkBox
						.setText("<HTML>Allow multiple votes</HTML>");
				allowMultipleChkBox
						.setFont(new java.awt.Font("Segoe UI", 0, 9));
				allowMultipleChkBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
				allowMultipleChkBox.setSelected(true);
				allowMultipleChkBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						allowMultipleChkBoxActionPerformed(evt);
					}
				});
			}
			{
				URLfield = new JTextField();
			}
			{
				fetchBtn = new JButton();
				fetchBtn.setText("Fetch!");
				fetchBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
				fetchBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						fetchBtnActionPerformed(evt);
					}
				});
			}
			{
				statusLabel = new JLabel();
				statusLabel.setFont(new java.awt.Font("Arial Narrow", 0, 12));
				statusLabel.setText(Manager.status);
			}
			{
				votePanel = new JPanel();
				BorderLayout votePanelLayout = new BorderLayout();
				votePanel.setLayout(votePanelLayout);
				votePanel.setBackground(new java.awt.Color(0, 0, 255));
				{
					voteSplitVertical = new JSplitPane();
					votePanel.add(voteSplitVertical);
					voteSplitVertical.setOrientation(JSplitPane.VERTICAL_SPLIT);
					voteSplitVertical.setDividerSize(1);
					voteSplitVertical.setDividerLocation(25);
					voteSplitVertical.setEnabled(false);
					voteSplitVertical.setBorder(new LineBorder(
							new java.awt.Color(0, 0, 0), 0, false));
					{
						sortPanel = new JPanel();
						voteSplitVertical.add(sortPanel, JSplitPane.LEFT);
						sortPanel.setMaximumSize(new java.awt.Dimension(32767,
								25));
						sortPanel.setLayout(null);
						sortPanel
								.setMinimumSize(new java.awt.Dimension(175, 25));
						{
							sortLabel = new JLabel();
							sortPanel.add(sortLabel);
							sortLabel.setText("Sort by:");
							sortLabel.setBounds(4, 0, 55, 24);
						}
						{
							ptsSort = new JButton();
							sortPanel.add(ptsSort);
							ptsSort.setText("user");
							ptsSort.setIconTextGap(0);
							ptsSort.setFont(new java.awt.Font("Segoe UI", 0, 11));
							ptsSort.setBounds(90, 3, 41, 21);
							ptsSort.setMargin(new java.awt.Insets(0, 0, 0, 0));
							ptsSort.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out
											.println("ptsSort.actionPerformed, event="
													+ evt);
									Manager.sortByPoster();
									Manager.updateVoteLists();
								}
							});
						}
						{
							numSort = new JButton();
							sortPanel.add(numSort);
							numSort.setText("##");
							numSort.setAlignmentY(0.0f);
							numSort.setFont(new java.awt.Font("Segoe UI", 0, 11));
							numSort.setBounds(53, 3, 41, 21);
							numSort.setMargin(new java.awt.Insets(0, 0, 0, 0));
							numSort.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out
											.println("numSort.actionPerformed, event="
													+ evt);
									Manager.sortByPostNum();
									Manager.updateVoteLists();
								}
							});
						}
						{
							voteSort = new JButton();
							sortPanel.add(voteSort);
							voteSort.setText("vote");
							voteSort.setIconTextGap(0);
							voteSort.setFont(new java.awt.Font("Segoe UI", 0,
									11));
							voteSort.setBounds(129, 3, 41, 21);
							voteSort.setMargin(new java.awt.Insets(0, 0, 0, 0));
							voteSort.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									System.out
											.println("voteSort.actionPerformed, event="
													+ evt);
									Manager.sortByVoteName();
									Manager.updateVoteLists();
								}
							});
						}
					}
					{
						voteScrollPane = new JScrollPane();
						voteSplitVertical
								.add(voteScrollPane, JSplitPane.BOTTOM);
						voteScrollPane.setPreferredSize(new java.awt.Dimension(
								190, 161));
						voteScrollPane.getVerticalScrollBar().setUnitIncrement(
								8);
						{
							voteSplitPane1 = new JSplitPane();
							voteScrollPane.setViewportView(voteSplitPane1);
							voteSplitPane1.setDividerSize(1);
							voteSplitPane1.setEnabled(false);
							voteSplitPane1.setBorder(new LineBorder(
									new java.awt.Color(0, 0, 0), 0, false));
							{
								voteSplitPane2 = new JSplitPane();
								voteSplitPane1.add(voteSplitPane2,
										JSplitPane.RIGHT);
								voteSplitPane2.setDividerSize(5);
								voteSplitPane2.setBorder(new LineBorder(
										new java.awt.Color(0, 0, 0), 0, false));
								voteSplitPane2.setAutoscrolls(true);
								voteSplitPane2.setContinuousLayout(true);
								{
									ListModel voteListModel = new DefaultComboBoxModel(
											new String[] { "" });
									voteList = new JList();
									voteSplitPane2.add(getVoteList(),
											JSplitPane.RIGHT);
									voteList.setCellRenderer(new AlternatingCellRenderer());
									voteList.setModel(voteListModel);
									voteList.setBackground(new java.awt.Color(
											255, 255, 255));
									voteList.setName("vot");
									voteList.addListSelectionListener(new ListSelectionListener() {
										public void valueChanged(
												ListSelectionEvent evt) {
											voteListValueChanged(evt);
										}
									});
								}
								{
									ListModel posterListModel = new DefaultComboBoxModel(
											new String[] { "" });
									posterList = new JList();
									voteSplitPane2.add(getPosterList(),
											JSplitPane.LEFT);
									posterList
											.setCellRenderer(new AlternatingCellRenderer());
									posterList.setModel(posterListModel);
									posterList.setName("pos");
									posterList
											.setMinimumSize(new java.awt.Dimension(
													65, 0));
									posterList
											.addListSelectionListener(new ListSelectionListener() {
												public void valueChanged(
														ListSelectionEvent evt) {
													posterListValueChanged(evt);
												}
											});
								}
							}
							{
								ListModel numListModel = new DefaultComboBoxModel(
										new String[] { "" });
								numList = new JList();
								voteSplitPane1.add(getNumList(),
										JSplitPane.LEFT);
								numList.setModel(numListModel);
								numList.setCellRenderer(new AlternatingCellRenderer());
								numList.setName("num");
								numList.setMinimumSize(new java.awt.Dimension(
										25, 0));
								numList.addListSelectionListener(new ListSelectionListener() {
									public void valueChanged(
											ListSelectionEvent evt) {
										numListValueChanged(evt);
									}
								});
							}
						}
					}
				}
			}
			{
				userTabbedPane = new JTabbedPane();
				userTabbedPane
						.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				userTabbedPane.setFont(new java.awt.Font("Segoe UI", 1, 12));
				{
					userPanel = new JPanel();
					BorderLayout userPanelLayout = new BorderLayout();
					userTabbedPane.addTab("Users", null, userPanel, null);
					userPanel
							.setPreferredSize(new java.awt.Dimension(116, 176));
					userPanel.setLayout(userPanelLayout);
					{
						userScrollPane = new JScrollPane();
						userPanel.add(userScrollPane, BorderLayout.CENTER);
						userScrollPane.setPreferredSize(new java.awt.Dimension(
								102, 177));
						{
							ListModel userListModel = new DefaultComboBoxModel(
									new String[] {});
							userList = new JList();
							userScrollPane.setViewportView(userList);
							userList.setCellRenderer(new AlternatingCellRenderer());
							userList.setModel(userListModel);
							userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							userList.setDebugGraphicsOptions(DebugGraphics.NONE_OPTION);
							userList.setLayout(null);
							userList.addListSelectionListener(new ListSelectionListener() {
								public void valueChanged(ListSelectionEvent evt) {
									userListValueChanged(evt);
								}
							});
						}
					}
				}
				{
					editUsersPanel = new JPanel();
					GroupLayout editUsersPanelLayout = new GroupLayout(
							(JComponent) editUsersPanel);
					userTabbedPane.addTab("Edit", null, editUsersPanel, null);
					editUsersPanel.setLayout(editUsersPanelLayout);
					{
						applyEditBtn = new JButton();
						applyEditBtn.setText("Apply");
						applyEditBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
						applyEditBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								applyEditBtnActionPerformed(evt);
							}
						});
					}
					{
						editUsersTip = new JLabel();
						editUsersTip
								.setText("<HTML>Paste userlist with <BR>newlines between each</HTML>");
						editUsersTip.setFont(new java.awt.Font("Segoe UI", 0,
								10));
					}
					editUsersPanelLayout
							.setHorizontalGroup(editUsersPanelLayout
									.createParallelGroup()
									.addComponent(applyEditBtn,
											GroupLayout.Alignment.LEADING,
											GroupLayout.PREFERRED_SIZE, 50,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(editUsersTip,
											GroupLayout.Alignment.LEADING,
											GroupLayout.PREFERRED_SIZE, 102,
											GroupLayout.PREFERRED_SIZE)
									.addComponent(getJScrollPane2(),
											GroupLayout.Alignment.LEADING, 0,
											106, Short.MAX_VALUE));
					editUsersPanelLayout.setVerticalGroup(editUsersPanelLayout
							.createSequentialGroup()
							.addComponent(getJScrollPane2(), 0, 120,
									Short.MAX_VALUE)
							.addComponent(editUsersTip,
									GroupLayout.PREFERRED_SIZE, 27,
									GroupLayout.PREFERRED_SIZE)
							.addComponent(applyEditBtn,
									GroupLayout.PREFERRED_SIZE, 23,
									GroupLayout.PREFERRED_SIZE)
							.addContainerGap(6, 6));
				}
			}
			{
				functionPanel = new JPanel();
				functionPanel.setLayout(null);
				functionPanel.add(getReplaceBtn());
				{
					deleteBtn = new JButton();
					functionPanel.add(deleteBtn);
					functionPanel.add(getUndoBtn());
					functionPanel.add(getUndoLabel());
					functionPanel.add(getCorrectBtn());
					deleteBtn.setText("Delete");
					deleteBtn.setBounds(6, 75, 58, 21);
					deleteBtn.setBackground(new java.awt.Color(255, 128, 128));
					deleteBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
					deleteBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							deleteBtnActionPerformed(evt);
						}
					});
				}
			}
			{
				votesLabel = new JLabel();
				votesLabel.setLayout(null);
				votesLabel.setText("Collected Votes");
				votesLabel.setFont(new java.awt.Font("Segoe UI", 1, 12));
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				            .addComponent(URLfield, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(urlLaber, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(fetchBtn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
				                .addGap(9))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(10)
				                .addComponent(getWarningLabel(), GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(20)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(allowMultipleChkBox, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
				            .addComponent(statusLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addComponent(getTimeLimitChkBox(), GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(votesLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addComponent(getCountLabel(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(votePanel, GroupLayout.Alignment.LEADING, 0, 184, Short.MAX_VALUE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addGap(0, 35, Short.MAX_VALUE)
				                .addComponent(functionPanel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))))
				    .addComponent(userTabbedPane, GroupLayout.Alignment.LEADING, 0, 200, Short.MAX_VALUE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(rLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(rtextLbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(dtextLbl, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
				    .addComponent(dLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)));
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(dtextLbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				            .addComponent(rtextLbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(dLabel, GroupLayout.Alignment.LEADING, 0, 385, Short.MAX_VALUE)
				            .addComponent(rLabel, GroupLayout.Alignment.LEADING, 0, 385, Short.MAX_VALUE)))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(votePanel, 0, 232, Short.MAX_VALUE)
				        .addComponent(functionPanel, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
				        .addComponent(userTabbedPane, 0, 109, Short.MAX_VALUE)
				        .addGap(0, 9, GroupLayout.PREFERRED_SIZE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addComponent(votesLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
				        .addComponent(fetchBtn, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
				        .addGap(17)
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(getCountLabel(), GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
				                .addGap(0, 109, Short.MAX_VALUE))
				            .addComponent(statusLabel, GroupLayout.Alignment.LEADING, 0, 215, Short.MAX_VALUE)
				            .addComponent(getWarningLabel(), GroupLayout.Alignment.LEADING, 0, 215, Short.MAX_VALUE))
				        .addGap(9))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(allowMultipleChkBox, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(getTimeLimitChkBox(), GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
				                .addGap(31))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(urlLaber, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
				                .addGap(43)))
				        .addComponent(URLfield, 0, 300, Short.MAX_VALUE)
				        .addGap(0, 12, GroupLayout.PREFERRED_SIZE))));
			pack();
			this.setSize(450, 375);
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	public JTextField getURLfield() {
		return URLfield;
	}

	public JPanel getVotePanel() {
		return votePanel;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JPanel getFunctionPanel() {
		return functionPanel;
	}

	public JPanel getUserPanel() {
		return userPanel;
	}

	public JList getUserList() {
		return userList;
	}

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	public JList getVoteList() {
		return voteList;
	}

	public JList getPosterList() {
		return posterList;
	}

	public JList getNumList() {
		return numList;
	}

	public JLabel getRLabel() {
		return rLabel;
	}

	public JLabel getDLabel() {
		return dLabel;
	}

	public JLabel getRLabelx() {
		return rLabel;
	}

	public JLabel getDLabelx() {
		return dLabel;
	}

	private void numListValueChanged(ListSelectionEvent evt) {
		System.out.println("numList.valueChanged, event=" + evt);
		processMessages();
	}

	private void posterListValueChanged(ListSelectionEvent evt) {
		System.out.println("posterList.valueChanged, event=" + evt);
		processMessages();
	}

	private void voteListValueChanged(ListSelectionEvent evt) {
		// System.out.println("voteList.valueChanged, event=" + evt);
		processMessages();
	}

	private void userListValueChanged(ListSelectionEvent evt) {
		System.out.println("userList.valueChanged, event=" + evt);
		processMessages();
	}

	private synchronized void processMessages() {
		String numsla = spa, possla = spa, votsla = spa;
		numSelection = "post ";
		posterSelection = "";
		voteSelection = "vote ";

		if (numList.getSelectedIndices().length > 1) {
			numSelection = "posts ";
			numsla = sla;
		}
		if (posterList.getSelectedIndices().length > 0
				&& numList.getSelectedIndices().length > 0) {
			posterSelection = " and of poster ";
			if (posterList.getSelectedIndices().length > 1) {
				posterSelection = " and of posters ";
				possla = sla;
			}
		} else if (posterList.getSelectedIndices().length > 0
				&& numList.getSelectedIndex() == -1) {
			numSelection = "";
			posterSelection = "poster ";
			if (posterList.getSelectedIndices().length > 1) {
				posterSelection = "posters ";
				possla = sla;
			}
		}
		if (voteList.getSelectedIndices().length > 1) {
			voteSelection = "votes ";
			votsla = sla;
		}

		for (Object s : numList.getSelectedValues())
			numSelection += ((Vote) s).toString(1) + numsla;
		for (Object s : posterList.getSelectedValues())
			posterSelection += ((Vote) s).toString(2) + possla;
		for (Object s : voteList.getSelectedValues())
			voteSelection += ((Vote) s).toString(3) + votsla;

		if (numList.getSelectedIndex() == -1
				&& posterList.getSelectedIndex() == -1) {
			numSelection = "SELECT POST NUMBER(S) or ";
			posterSelection = "SELECT POSTER USERNAME(S)";
		}
		if (voteList.getSelectedIndex() == -1)
			voteSelection = "SELECT VOTE(S)";
		if (userList.getSelectedIndex() == -1)
			userSelection = "SELECT USER";
		else
			userSelection = userList.getSelectedValue().toString();

		if (voteList.getSelectedIndex() != -1)
			updateToReplace();
		rLabel.setText(voteSelection + " with " + userSelection);
		dLabel.setText(numSelection + posterSelection);
	}

	private JButton getReplaceBtn() {
		if (replaceBtn == null) {
			replaceBtn = new JButton();
			replaceBtn.setText("Replace");
			replaceBtn.setBounds(6, 50, 58, 21);
			replaceBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
			replaceBtn.setBackground(new java.awt.Color(117, 117, 255));
			replaceBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					replaceBtnActionPerformed(evt);
				}
			});
		}
		return replaceBtn;
	}

	public JButton getUndoBtn() {
		if (undoBtn == null) {
			undoBtn = new JButton();
			undoBtn.setText("Undo");
			undoBtn.setBounds(6, 110, 58, 21);
			undoBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
			undoBtn.setBackground(new java.awt.Color(255, 255, 128));
			undoBtn.setEnabled(false);
			undoBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					undoBtnActionPerformed(evt);
				}
			});
		}
		return undoBtn;
	}

	public JTextArea getEditUserArea() {
		if (editUserArea == null) {
			editUserArea = new JTextArea();
			editUserArea.setWrapStyleWord(true);
			editUserArea.setLineWrap(true);
		}
		return editUserArea;
	}

	private JScrollPane getJScrollPane2() {
		if (editUserScrollPane == null) {
			editUserScrollPane = new JScrollPane();
			editUserScrollPane.setViewportView(getEditUserArea());
		}
		return editUserScrollPane;
	}

	private JLabel getUndoLabel() {
		if (undoLabel == null) {
			undoLabel = new JLabel();
			undoLabel.setText("ctrl+z");
			undoLabel.setBounds(24, 130, 34, 14);
			undoLabel.setFont(new java.awt.Font("Arial Narrow", 0, 10));
		}
		return undoLabel;
	}

	private JMenuItem getEditUndo() {
		if (editUndo == null) {
			editUndo = new JMenuItem();
			editUndo.setText("Undo");
			editUndo.setAccelerator(KeyStroke.getKeyStroke("ctrl pressed Z"));
			editUndo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out
							.println("editUndo.actionPerformed, event=" + evt);
					undoBtnActionPerformed(evt);
				}
			});
		}
		return editUndo;
	}

	private void thisWindowClosing(WindowEvent evt) {
		System.out.println("this.windowClosing, event=" + evt);
		windowClosing();
	}

	private void windowClosing() {
		getCloseDialog().setLocationRelativeTo(this);
		getCloseDialog().setVisible(true);
	}

	private JDialog getCloseDialog() {
		if (closeDialog == null) {
			closeDialog = new JDialog(this);
			closeDialog.setPreferredSize(new java.awt.Dimension(198, 115));
			closeDialog.setResizable(false);
			closeDialog.getContentPane().setLayout(null);
			closeDialog.setTitle("Exit");
			closeDialog.getContentPane().add(getCloseLabel(), "Center");
			closeDialog.getContentPane().add(getCloseBtn());
			closeDialog.getContentPane().add(getCancelBtn());
			closeDialog.setSize(198, 115);
		}
		return closeDialog;
	}

	private JLabel getCloseLabel() {
		if (closeLabel == null) {
			closeLabel = new JLabel();
			closeLabel.setText("Close for suuuuuure?");
			closeLabel.setBounds(4, 12, 128, 21);
		}
		return closeLabel;
	}

	private JButton getCloseBtn() {
		if (closeBtn == null) {
			closeBtn = new JButton();
			closeBtn.setText("Ok");
			closeBtn.setBounds(12, 45, 39, 23);
			closeBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
			closeBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					closeBtnActionPerformed(evt);
				}
			});
			closeBtn.registerKeyboardAction(closeBtn
					.getActionForKeyStroke(KeyStroke.getKeyStroke(
							KeyEvent.VK_SPACE, 0, false)), KeyStroke
					.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);
			closeBtn.registerKeyboardAction(closeBtn
					.getActionForKeyStroke(KeyStroke.getKeyStroke(
							KeyEvent.VK_SPACE, 0, true)), KeyStroke
					.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
		}
		return closeBtn;
	}

	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = new JButton();
			cancelBtn.setText("Cancel");
			cancelBtn.setBounds(62, 45, 54, 23);
			cancelBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
			cancelBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					cancelBtnActionPerformed(evt);
				}
			});
			cancelBtn.registerKeyboardAction(cancelBtn
					.getActionForKeyStroke(KeyStroke.getKeyStroke(
							KeyEvent.VK_SPACE, 0, false)), KeyStroke
					.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
					JComponent.WHEN_FOCUSED);
			cancelBtn.registerKeyboardAction(cancelBtn
					.getActionForKeyStroke(KeyStroke.getKeyStroke(
							KeyEvent.VK_SPACE, 0, true)), KeyStroke
					.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
					JComponent.WHEN_FOCUSED);
		}
		return cancelBtn;
	}

	private void closeBtnActionPerformed(ActionEvent evt) {
		System.out.println("closeBtn.actionPerformed, event=" + evt);
		this.dispose();
		System.exit(0);
	}

	private void cancelBtnActionPerformed(ActionEvent evt) {
		System.out.println("cancelBtn.actionPerformed, event=" + evt);
		closeDialog.setVisible(false);
	}

	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(this);
			AnchorLayout aboutDialogLayout = new AnchorLayout();
			aboutDialog.getContentPane().setLayout(aboutDialogLayout);
			aboutDialog.setPreferredSize(new java.awt.Dimension(256, 289));
			aboutDialog.setTitle("About UotYMatic");
			aboutDialog.setResizable(false);
			aboutDialog.getContentPane().add(
					getAbout6(),
					new AnchorConstraint(185, 466, 415, 26,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
			aboutDialog.getContentPane().add(getAbout3(), new AnchorConstraint(442, 410, 871, 26, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			aboutDialog.getContentPane().add(
					getAbout1(),
					new AnchorConstraint(24, 874, 124, 106,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
			aboutDialog.getContentPane().add(
					getAbout2(),
					new AnchorConstraint(124, 794, 185, 154,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
			aboutDialog.getContentPane().add(getAbout4(), new AnchorConstraint(438, 842, 814, 458, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			aboutDialog.getContentPane().add(
					getAbout5(),
					new AnchorConstraint(894, 794, 955, 174,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL,
							AnchorConstraint.ANCHOR_REL));
			aboutDialog.setSize(256, 289);
		}
		return aboutDialog;
	}

	private void fileAboutActionPerformed(ActionEvent evt) {
		System.out.println("fileAbout.actionPerformed, event=" + evt);
		Manager.test();
		getAboutDialog().setLocationRelativeTo(this);
		getAboutDialog().setVisible(true);
	}

	private JLabel getAbout1() {
		if (about1 == null) {
			about1 = new JLabel();
			about1.setText("UotYmatic v2011");
			about1.setFont(new java.awt.Font("Segoe UI", 3, 22));
			about1.setPreferredSize(new java.awt.Dimension(192, 26));
		}
		return about1;
	}

	private JLabel getAbout2() {
		if (about2 == null) {
			about2 = new JLabel();
			about2.setText("by Momin \"foolmoron\" Khan");
			about2.setFont(new java.awt.Font("Segoe UI", 0, 12));
			about2.setPreferredSize(new java.awt.Dimension(160, 16));
		}
		return about2;
	}

	private JLabel getAbout3() {
		if (about3 == null) {
			about3 = new JLabel();
			about3.setText("<HTML>Credits :<BR>Concept - foolmoron<BR>Design - foolmoron<BR>GUI - foolmoron<BR>Coding - foolmoron<BR>(except for some code from a guy on a 3-year-old forum)</HTML>");
			about3.setFont(new java.awt.Font("Segoe UI", 0, 10));
			about3.setPreferredSize(new java.awt.Dimension(96, 112));
		}
		return about3;
	}

	private JLabel getAbout4() {
		if (about4 == null) {
			about4 = new JLabel();
			about4.setText("<HTML>Shortcuts :<BR>Ctrl+R - Replace<BR>Ctrl+D - Delete<BR>Ctrl+Z Undo<br>Hold ctrl/shift to select multiple items in the lists</HTML>");
			about4.setFont(new java.awt.Font("Segoe UI", 0, 10));
			about4.setPreferredSize(new java.awt.Dimension(96, 98));
		}
		return about4;
	}

	private JLabel getAbout5() {
		if (about5 == null) {
			about5 = new JLabel();
			about5.setText("VOTE foolmo UOTY 2011");
			about5.setFont(new java.awt.Font("Segoe UI", 1, 12));
			about5.setPreferredSize(new java.awt.Dimension(155, 16));
		}
		return about5;
	}

	@SuppressWarnings("deprecation")
	private void replaceBtnActionPerformed(ActionEvent evt) {
		if (voteList.getSelectedIndex() == -1) {
			Manager.warning = "Select a vote and a user to replace";
			Manager.updateStatus();
			return;
		} else if (userList.getSelectedIndex() == -1){
			updateToReplace();
			getReplaceDialogTextField().setText("");
			getReplaceDialog().setLocationRelativeTo(this);
			getReplaceDialog().setVisible(true);
			return;
		}
		System.out.println("REPLACED, event=" + evt);
		Manager.replace(voteList.getSelectedValues(),
				userList.getSelectedValue());
	}

	private void deleteBtnActionPerformed(ActionEvent evt) {
		System.out.println("deletebtn.event, event=" + evt);
		if (numList.getSelectedIndex() != -1
				|| posterList.getSelectedIndex() != -1) {
			ArrayList<Vote> votes = new ArrayList<Vote>();
			for (int i = 0; i < numList.getSelectedValues().length; i++)
				votes.add((Vote) numList.getSelectedValues()[i]);
			for (int i = 0; i < posterList.getSelectedValues().length; i++)
				votes.add((Vote) posterList.getSelectedValues()[i]);
			Manager.delete(votes);
		} else {
			Manager.warning = "Select posts/users to delete";
			Manager.updateStatus();
		}
	}

	private void undoBtnActionPerformed(ActionEvent evt) {
		System.out.println("UNDOd, event=" + evt);
		Manager.undo();
	}

	public JLabel getWarningLabel() {
		if (warningLabel == null) {
			warningLabel = new JLabel(){
				public void setText(String text){
					super.setText(text);
					if (text != null)
						if (text.contains("votecount")) {
							this.setForeground(new Color(0, 0, 225));
						} else if (text.contains("Double")) {
							this.setForeground(new Color(255, 0, 0));
						} else if (text.contains("Duplicate")) {
							this.setForeground(new Color(25, 135, 25));
						} else if (text.contains("fishy")) {
							this.setForeground(new Color(255, 75, 0));
						} else {
							this.setForeground(new Color(255, 0, 0));
						}
				}
			};
			warningLabel.setText(Manager.warning);
			warningLabel.setForeground(new java.awt.Color(255, 0, 0));
			warningLabel.setFont(new java.awt.Font("Segoe UI", 1, 12));
		}
		return warningLabel;
	}

	private JMenuItem getFileReload() {
		if (fileReload == null) {
			fileReload = new JMenuItem();
			fileReload.setText("Reload Lists");
			fileReload.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					System.out.println("fileReload.actionPerformed, event="
							+ evt);
					Manager.loadVotes();
				}
			});
		}
		return fileReload;
	}

	private void fetchBtnActionPerformed(ActionEvent evt) {
		System.out.println("fetchBtn.actionPerformed, event=" + evt);
		Manager.fetch();
	}

	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Help");
			{
				fileAbout = new JMenuItem();
				helpMenu.add(fileAbout);
				fileMenu.add(getFileReload());
				fileMenu.add(getFileVoteParameters());
				{
					sep1 = new JSeparator();
					fileMenu.add(sep1);
				}
				{
					fileClose = new JMenuItem();
					fileMenu.add(fileClose);
					fileClose.setText("Close");
					fileClose.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							fileCloseActionPerformed(evt);
						}
					});
				}
				fileAbout.setText("About");
				fileAbout.setBounds(-89, 56, 94, 21);
				fileAbout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						fileAboutActionPerformed(evt);
					}
				});
			}
		}
		return helpMenu;
	}

	private void applyEditBtnActionPerformed(ActionEvent evt) {
		System.out.println("applyEditBtn.actionPerformed, event=" + evt);
		Manager.updateUserList();
		userTabbedPane.setSelectedIndex(0);
	}

	public JButton getFetchBtn() {
		return fetchBtn;
	}

	private void fileCloseActionPerformed(ActionEvent evt) {
		System.out.println("fileClose.actionPerformed, event=" + evt);
		windowClosing();
	}

	private JLabel getAbout6() {
		if (about6 == null) {
			about6 = new JLabel();
			about6.setText("<HTML>Tools:<BR>Java SE 6<BR>Eclipse Helios IDE<BR>Jigloo GUI editor</HTML>");
			about6.setFont(new java.awt.Font("Segoe UI", 0, 11));
			about6.setPreferredSize(new java.awt.Dimension(110, 60));
		}
		return about6;
	}

	private JCheckBox getTimeLimitChkBox() {
		if (timeLimitChkBox == null) {
			timeLimitChkBox = new JCheckBox();
			timeLimitChkBox.setText("24 Hour Limit");
			timeLimitChkBox.setFont(new java.awt.Font("Segoe UI", 0, 9));
			timeLimitChkBox.setSelected(false);
			timeLimitChkBox.setMargin(new java.awt.Insets(0, 0, 0, 0));
			timeLimitChkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					timeLimitChkBoxActionPerformed(evt);
				}
			});
		}
		return timeLimitChkBox;
	}

	private void timeLimitChkBoxActionPerformed(ActionEvent evt) {
		System.out.println("timeLimitChkBox.actionPerformed, event=" + evt);
		Manager.oneDayLimit = timeLimitChkBox.isSelected();
		Manager.loadVotes();
	}

	private void allowMultipleChkBoxActionPerformed(ActionEvent evt) {
		System.out.println("allowMultipleChkBox.actionPerformed, event=" + evt);
		Manager.allowMultipleVotes = allowMultipleChkBox.isSelected();
		Manager.loadVotes();
	}

	private void graphsVotalsActionPerformed(ActionEvent evt) {
		System.out.println("graphsVotals.actionPerformed, event=" + evt);
		getVotalsDialog().setLocationRelativeTo(this);
		getVotalsDialog().setVisible(true);
		Manager.printVotals();
	}

	public JDialog getVotalsDialog() {
		if (votalsDialog == null) {
			votalsDialog = new JDialog(this);
			votalsDialog.setPreferredSize(new java.awt.Dimension(244, 251));
			votalsDialog.setTitle("Vote Totals");
			votalsDialog.getContentPane().add(getVotalsScrollPane(),
					BorderLayout.CENTER);
			votalsDialog.setSize(244, 251);
		}
		return votalsDialog;
	}

	public JTextArea getVotalsArea() {
		if (votalsArea == null) {
			votalsArea = new JTextArea();
		}
		return votalsArea;
	}

	public JScrollPane getVotalsScrollPane() {
		if (votalsScrollPane == null) {
			votalsScrollPane = new JScrollPane();
			votalsScrollPane.setPreferredSize(new java.awt.Dimension(204, 275));
			votalsScrollPane.setViewportView(getVotalsArea());
		}
		return votalsScrollPane;
	}

	private JMenuItem getGraphsVotals() {
		if (graphsVotals == null) {
			graphsVotals = new JMenuItem();
			graphsVotals.setText("Print Vote Totals...");
			graphsVotals.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					graphsVotalsActionPerformed(evt);
				}
			});
		}
		return graphsVotals;
	}

	private JButton getCorrectBtn() {
		if (correctBtn == null) {
			correctBtn = new JButton();
			correctBtn.setText("Correct");
			correctBtn.setBounds(6, 25, 58, 21);
			correctBtn.setBackground(new java.awt.Color(150, 255, 45));
			correctBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
			correctBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					correctBtnActionPerformed(evt);
				}
			});
		}
		return correctBtn;
	}

	private void correctBtnActionPerformed(ActionEvent evt) {
		System.out.println("correctBtn.actionPerformed, event=" + evt);
		if (numList.getSelectedIndex() != -1
				|| posterList.getSelectedIndex() != -1) {
			if (numList.getSelectedIndex() != -1)
				Manager.correctLoad((Vote) numList.getSelectedValues()[0]);
			else if (posterList.getSelectedIndex() != -1)
				Manager.correctLoad((Vote) posterList.getSelectedValues()[0]);
		} else {
			Manager.warning = "Select post/user to correct vote";
			Manager.updateStatus();
		}
	}

	public JDialog getCorrectDialog() {
		if (correctDialog == null) {
			correctDialog = new JDialog(this);
			correctDialog.setResizable(false);
			correctDialog.setTitle("Correct");
			correctDialog.getContentPane().setLayout(null);
			correctDialog.getContentPane().add(getJScrollPane1(), "Center");
			correctDialog.getContentPane().add(getApplyCorrect());
			correctDialog.setSize(274, 223);
		}
		return correctDialog;
	}

	public JTextArea getCorrectArea() {
		if (correctArea == null) {
			correctArea = new JTextArea();
		}
		return correctArea;
	}

	private JScrollPane getJScrollPane1() {
		if (correctScrollPane == null) {
			correctScrollPane = new JScrollPane();
			correctScrollPane.setBounds(0, 0, 268, 167);
			correctScrollPane.setViewportView(getCorrectArea());
		}
		return correctScrollPane;
	}

	private JButton getApplyCorrect() {
		if (applyCorrect == null) {
			applyCorrect = new JButton();
			applyCorrect.setText("Apply");
			applyCorrect.setBounds(6, 167, 45, 23);
			applyCorrect.setMargin(new java.awt.Insets(0, 0, 0, 0));
			applyCorrect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					applyCorrectActionPerformed(evt);
				}
			});
		}
		return applyCorrect;
	}

	private void applyCorrectActionPerformed(ActionEvent evt) {
		System.out.println("applyCorrect.actionPerformed, event=" + evt);
		Manager.correctSave(getCorrectArea().getText());
		getCorrectDialog().dispose();
	}

	public JLabel getCountLabel() {
		if (countLabel == null) {
			countLabel = new JLabel();
			countLabel.setText("");
			countLabel.setFont(new java.awt.Font("Arial Narrow", 1, 12));
		}
		return countLabel;
	}

	private JMenuItem getEditCorrect() {
		if (editCorrect == null) {
			editCorrect = new JMenuItem();
			editCorrect.setText("Correct");
			editCorrect.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					editCorrectActionPerformed(evt);
				}
			});
		}
		return editCorrect;
	}

	private void editCorrectActionPerformed(ActionEvent evt) {
		System.out.println("editCorrect.actionPerformed, event=" + evt);
		correctBtnActionPerformed(evt);
	}

	private JMenuItem getFileVoteParameters() {
		if (fileVoteParameters == null) {
			fileVoteParameters = new JMenuItem();
			fileVoteParameters.setText("Vote Parameters...");
			fileVoteParameters.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					fileVoteParametersActionPerformed(evt);
				}
			});
		}
		return fileVoteParameters;
	}

	private void fileVoteParametersActionPerformed(ActionEvent evt) {
		System.out.println("fileVoteParameters.actionPerformed, event=" + evt);
		getVoteParameterDialog().setLocationRelativeTo(this);
		getVotesperpostField().setText("" + Post.getExpectedVoteCount());
		getVoteParameterDialog().setVisible(true);
	}

	public JDialog getVoteParameterDialog() {
		if (voteParameterDialog == null) {
			voteParameterDialog = new JDialog(this);
			voteParameterDialog.getContentPane().setLayout(null);
			voteParameterDialog.setTitle("Parameters");
			voteParameterDialog.setResizable(false);
			voteParameterDialog.setPreferredSize(new java.awt.Dimension(202, 120));
			voteParameterDialog.setAutoRequestFocus(true);
			voteParameterDialog.getContentPane()
					.add(getParamLabel1(), "Center");
			voteParameterDialog.getContentPane().add(getVotesperpostField());
			voteParameterDialog.getContentPane().add(getApplyParamBtn());
			voteParameterDialog.setSize(200, 110);
		}
		return voteParameterDialog;
	}

	private JLabel getParamLabel1() {
		if (paramLabel1 == null) {
			paramLabel1 = new JLabel();
			paramLabel1.setText("Votes per post:");
			paramLabel1.setBounds(8, 12, 86, 19);
		}
		return paramLabel1;
	}

	public JTextField getVotesperpostField() {
		if (votesperpostField == null) {
			votesperpostField = new JTextField();
			votesperpostField.setBounds(94, 10, 38, 23);
			votesperpostField.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					System.out.println("votesperpostField.keyPressed, event="+evt);
					if (evt.getKeyCode() == KeyEvent.VK_ENTER){
						applyParamBtnActionPerformed(evt);
					}
				}
			});
		}
		return votesperpostField;
	}

	private JButton getApplyParamBtn() {
		if (applyParamBtn == null) {
			applyParamBtn = new JButton();
			applyParamBtn.setText("Apply");
			applyParamBtn.setBounds(8, 48, 45, 23);
			applyParamBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
			applyParamBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					applyParamBtnActionPerformed(evt);
				}
			});
		}
		return applyParamBtn;
	}
	
	private void applyParamBtnActionPerformed(AWTEvent evt) {
		System.out.println("applyParamBtn.actionPerformed, event="+evt);
		Post.setExpectedVoteCount(Integer.parseInt(getVotesperpostField().getText()));
		Manager.fetch();
		getVoteParameterDialog().dispose();
	}
	
	public JDialog getReplaceDialog() {
		if(replaceDialog == null) {
			replaceDialog = new JDialog(this);
			replaceDialog.setPreferredSize(new java.awt.Dimension(240, 132));
			replaceDialog.getContentPane().setLayout(null);
			replaceDialog.setTitle("Replace");
			replaceDialog.getContentPane().add(getReplaceDialogLabel());
			replaceDialog.getContentPane().add(getReplaceDialogTextField());
			replaceDialog.getContentPane().add(getReplaceDialogApply());
			replaceDialog.setSize(240, 132);
		}
		return replaceDialog;
	}
	
	public JLabel getReplaceDialogLabel() {
		if(replaceDialogLabel == null) {
			replaceDialogLabel = new JLabel();
			replaceDialogLabel.setText("Replace _____ with:");
			replaceDialogLabel.setBounds(5, 9, 214, 16);
		}
		return replaceDialogLabel;
	}
	
	private JTextField getReplaceDialogTextField() {
		if(replaceDialogTextField == null) {
			replaceDialogTextField = new JTextField();
			replaceDialogTextField.setBounds(5, 35, 214, 23);
			replaceDialogTextField.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					System.out.println("replaceDialogTextField.keyPressed, event="+evt);
					if (evt.getKeyCode() == KeyEvent.VK_ENTER){
						replaceDialogApplyActionPerformed(evt);
					}
				}
			});
		}
		return replaceDialogTextField;
	}
	
	private JButton getReplaceDialogApply() {
		if(replaceDialogApply == null) {
			replaceDialogApply = new JButton();
			replaceDialogApply.setText("Apply");
			replaceDialogApply.setBounds(5, 64, 45, 23);
			replaceDialogApply.setMargin(new java.awt.Insets(0, 0, 0, 0));
			replaceDialogApply.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					replaceDialogApplyActionPerformed(evt);
				}
			});
		}
		return replaceDialogApply;
	}
	
	private void replaceDialogApplyActionPerformed(AWTEvent evt) {
		System.out.println("replaceDialogApply.actionPerformed, event="+evt);
		if (voteList.getSelectedIndex() != -1){
			((DefaultComboBoxModel)getUserList().getModel()).addElement(Manager.getUser(getReplaceDialogTextField().getText()));
			Manager.replace(voteList.getSelectedValues(), Manager.getUser(getReplaceDialogTextField().getText()));
		}
		getReplaceDialog().dispose();
	}
	
	private void updateToReplace(){
		String toReplace = ((Vote)voteList.getSelectedValues()[0]).getUser().getName();
		for (int i = 1; i < voteList.getSelectedValues().length; i++)
			toReplace += ", " + ((Vote)voteList.getSelectedValues()[i]).getUser().getName();
		getReplaceDialogLabel().setText("Replace " + toReplace + " with:");
	}

}

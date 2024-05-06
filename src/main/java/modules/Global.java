package modules;



import org.openqa.selenium.WebDriver;


public class Global {
	
	public static WebDriver driver;
	public static final String QC_PROJECT_NAME="ResponsiveED";
	public static final String fldSFolderID="";
	

	public static String ciCommitBranch=String.valueOf(System.getenv("CI_COMMIT_BRA")).replace("null","");
	public static String ciEmailFrom=String.valueOf(System.getenv("CI_EMAIL_FROM"));
	public static String ciEmailRecipients=String.valueOf(System.getenv("CI_EMAIL_RECIPIENTS"));
	public static String ciEmailSubject=String.valueOf(System.getenv("CI_EMAIL_SUBJECT"));
	public static String ciJobid=String.valueOf(System.getenv("CI_JOB_ID")).replace("null","");
	
	public static String ciPipelineUrl=String.valueOf(System.getenv("CI_PIPELINE_URL")).replace("null","");
	public static String ciPipelineId=String.valueOf(System.getenv("CI_PIPELINE_ID")).replace("null","");
	public static String ciProjecteName=String.valueOf(System.getenv("CI_PROJECT_NAME")).replace("null",ExcelWrite.getCurrentGitProject());
	public static String ciRunnerDesc=String.valueOf(System.getenv("CI_RUNNER_DESCRIPTION")).replace("null","");
	public static String ciScheduleDesc=String.valueOf(System.getenv("CI_SCHEDULE_DESCRIPTION")).replace("null","");
	public static String ciUserEmail=System.getenv("GITLAB_USER_EMAIL");
	public static String ciUserName=System.getenv("GITLAB_USER_NAME");
	public static String ciToolName=null;
	
}

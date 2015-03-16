package com.bizconf.vcaasz.entity;

/**
 * 
 * @author Chris Gao
 * 
 */
public class MS {

	public static final String OTHERS = "others";
	public static final String ALL = "all";

	int id;
	String serverIp;
	String serveForIsp;
	String serveForIspDownload;
	String serveForLocation;
	String domainName;
	int inUse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServeForIsp() {
		return serveForIsp;
	}

	public boolean isServeForIsp(String ispCode) {
		if (!this.isInUse()) {
			return false;
		}
		if (this.serveForIsp == null || this.serveForIsp.length() == 0) {
			return false;
		}
		if (ALL.equalsIgnoreCase(this.serveForIsp)) {
			return true;
		}
		if (this.serveForIsp.contains(ispCode)
				&& !this.serveForIsp.contains("!" + ispCode)) {
			return true;
		}
		if (serveForIsp.contains(OTHERS)
				&& !this.serveForIsp.contains("!" + ispCode)) {
			return true;
		}
		return false;
	}

	public boolean isServeForIspDownload(String ispCode) {
		if (!this.isInUse()) {
			return false;
		}
		if (this.serveForIspDownload == null
				|| this.serveForIspDownload.length() == 0) {
			return false;
		}
		if (ALL.equalsIgnoreCase(this.serveForIspDownload)) {
			return true;
		}
		if (this.serveForIspDownload.contains(ispCode)
				&& !this.serveForIspDownload.contains("!" + ispCode)) {
			return true;
		}
		if (serveForIspDownload.contains(OTHERS)
				&& !this.serveForIspDownload.contains("!" + ispCode)) {
			return true;
		}
		return false;
	}

	public void setServeForIsp(String serveForIsp) {
		this.serveForIsp = serveForIsp;
	}

	public String getServeForIspDownload() {
		return serveForIspDownload;
	}

	public void setServeForIspDownload(String serveForIspDownload) {
		this.serveForIspDownload = serveForIspDownload;
	}

	public String getServeForLocation() {
		return serveForLocation;
	}

	public void setServeForLocation(String serveForLocation) {
		this.serveForLocation = serveForLocation;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public int getInUse() {
		return inUse;
	}

	public void setInUse(int inUser) {
		this.inUse = inUser;
	}

	public boolean isInUse() {
		return this.inUse == 1;
	}
	
	public boolean equals(Object object){
		if(object==null){
			return false;
		}
		try{
			MS ms = (MS)object;
			if(this.getId()>0 && ms.getId()>0){
				if(this.getId()==ms.getId()){
					return true;
				}else{
					return false;
				}
			}
			return false;
		}catch (Exception e) {
			return false;
		}
	}
}

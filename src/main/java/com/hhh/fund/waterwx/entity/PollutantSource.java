package com.hhh.fund.waterwx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 污染源信息实体类
 * @author 3hzxp
 *
 */
@Entity
@Table(name="pollutant_source")
@NamedQuery(name="PollutantSource.findAll", query="SELECT r FROM PollutantSource r")
public class PollutantSource {
	
	@Id
	@GeneratedValue(generator="idGenerator")
	@GenericGenerator(name="idGenerator", strategy="uuid")
    private String id;

    private String rivername;
    
    private String rivercode;

    private String area;

    private String pollsourcename;

    private String streetname;

    private String streetmanager;

    private String village;

    private String villagemanager;

    private String pollsourcetype;

    private String outfalltype;

    private String outfallcode;

    private String position;

    private String coordinate;

    private String polldescription;

    private String drainageto;

    private String polldischarginglicense;

    private String drainaglicense;

    private String hasmeasures;

    private String rectificationmeasures;

    private String therectificationresponsibilityunit;

    private String timeofcompletion;
    
    private String industryoragriculture;

    private String remark;
    
    private Date createTime;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRivername() {
        return rivername;
    }

    public void setRivername(String rivername) {
        this.rivername = rivername == null ? null : rivername.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getPollsourcename() {
        return pollsourcename;
    }

    public void setPollsourcename(String pollsourcename) {
        this.pollsourcename = pollsourcename == null ? null : pollsourcename.trim();
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname == null ? null : streetname.trim();
    }

    public String getStreetmanager() {
        return streetmanager;
    }

    public void setStreetmanager(String streetmanager) {
        this.streetmanager = streetmanager == null ? null : streetmanager.trim();
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village == null ? null : village.trim();
    }

    public String getVillagemanager() {
        return villagemanager;
    }

    public void setVillagemanager(String villagemanager) {
        this.villagemanager = villagemanager == null ? null : villagemanager.trim();
    }

    public String getPollsourcetype() {
        return pollsourcetype;
    }

    public void setPollsourcetype(String pollsourcetype) {
        this.pollsourcetype = pollsourcetype == null ? null : pollsourcetype.trim();
    }

    public String getOutfalltype() {
        return outfalltype;
    }

    public void setOutfalltype(String outfalltype) {
        this.outfalltype = outfalltype == null ? null : outfalltype.trim();
    }

    public String getOutfallcode() {
        return outfallcode;
    }

    public void setOutfallcode(String outfallcode) {
        this.outfallcode = outfallcode == null ? null : outfallcode.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public String getPolldescription() {
        return polldescription;
    }

    public void setPolldescription(String polldescription) {
        this.polldescription = polldescription == null ? null : polldescription.trim();
    }

    public String getDrainageto() {
        return drainageto;
    }

    public void setDrainageto(String drainageto) {
        this.drainageto = drainageto == null ? null : drainageto.trim();
    }

    public String getPolldischarginglicense() {
        return polldischarginglicense;
    }

    public void setPolldischarginglicense(String polldischarginglicense) {
        this.polldischarginglicense = polldischarginglicense == null ? null : polldischarginglicense.trim();
    }

    public String getDrainaglicense() {
        return drainaglicense;
    }

    public void setDrainaglicense(String drainaglicense) {
        this.drainaglicense = drainaglicense == null ? null : drainaglicense.trim();
    }

    public String getHasmeasures() {
        return hasmeasures;
    }

    public void setHasmeasures(String hasmeasures) {
        this.hasmeasures = hasmeasures == null ? null : hasmeasures.trim();
    }

    public String getRectificationmeasures() {
        return rectificationmeasures;
    }

    public void setRectificationmeasures(String rectificationmeasures) {
        this.rectificationmeasures = rectificationmeasures == null ? null : rectificationmeasures.trim();
    }

    public String getTherectificationresponsibilityunit() {
        return therectificationresponsibilityunit;
    }

    public void setTherectificationresponsibilityunit(String therectificationresponsibilityunit) {
        this.therectificationresponsibilityunit = therectificationresponsibilityunit == null ? null : therectificationresponsibilityunit.trim();
    }

    public String getTimeofcompletion() {
        return timeofcompletion;
    }

    public void setTimeofcompletion(String timeofcompletion) {
        this.timeofcompletion = timeofcompletion == null ? null : timeofcompletion.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIndustryoragriculture() {
		return industryoragriculture;
	}

	public void setIndustryoragriculture(String industryoragriculture) {
		this.industryoragriculture = industryoragriculture;
	}

	public String getRivercode() {
		return rivercode;
	}

	public void setRivercode(String rivercode) {
		this.rivercode = rivercode;
	}

	@Override
	public String toString() {
		return "PollutantSource [id=" + id + ", rivername=" + rivername + ", area=" + area + ", pollsourcename="
				+ pollsourcename + ", streetname=" + streetname + ", streetmanager=" + streetmanager + ", village="
				+ village + ", villagemanager=" + villagemanager + ", pollsourcetype=" + pollsourcetype
				+ ", outfalltype=" + outfalltype + ", outfallcode=" + outfallcode + ", position=" + position
				+ ", coordinate=" + coordinate + ", polldescription=" + polldescription + ", drainageto=" + drainageto
				+ ", polldischarginglicense=" + polldischarginglicense + ", drainaglicense=" + drainaglicense
				+ ", hasmeasures=" + hasmeasures + ", rectificationmeasures=" + rectificationmeasures
				+ ", therectificationresponsibilityunit=" + therectificationresponsibilityunit + ", timeofcompletion="
				+ timeofcompletion + ", remark=" + remark + "]";
	}
    
}
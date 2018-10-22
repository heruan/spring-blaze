package to.lova.spring.blaze.view;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.blazebit.persistence.view.CreatableEntityView;
import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.UpdatableEntityView;
import com.blazebit.persistence.view.UpdatableMapping;

import to.lova.spring.blaze.model.ServiceDetail;
import to.lova.spring.blaze.model.Switchboard;

@EntityView(ServiceDetail.class)
@CreatableEntityView
@UpdatableEntityView
public interface ServiceDetailView extends Serializable {

    public String getServiceHours();

    public void setServiceHours(String serviceHours);

    public String getServiceNote();

    public void setServiceNote(String serviceNote);

    public LocalDate getInstallationDate();

    public void setInstallationDate(LocalDate installationDate);

    public String getSwitchboardRelease();

    public void setSwitchboardRelease(String switchboardRelease);

    public String getSwitchboardVersion();

    public void setSwitchboardVersion(String switchboardVersion);

    public String getNumber();

    public void setNumber(String number);

    public String getPassword();

    public void setPassword(String password);

    public String getCpuCode();

    public void setCpuCode(String cpuCode);

    public boolean isGreetingSystem();

    public void setGreetingSystem(boolean greetingSystem);

    public String getSwitchboardNote();

    public void setSwitchboardNote(String switchboardNote);

    public String getVpnType();

    public void setVpnType(String vpnType);

    public String getVpnAddress();

    public void setVpnAddress(String vpnAddress);

    public String getVpnUsername();

    public void setVpnUsername(String vpnUsername);

    public String getVpnPassword();

    public void setVpnPassword(String vpnPassword);

    public String getVpnNote();

    public void setVpnNote(String vpnNote);

    public UserDetail getTechnician();

    public void setTechnician(UserDetail technician);

    public Switchboard getSwitchboard();

    public void setSwitchboard(Switchboard switchboard);

    @UpdatableMapping
    public List<SwitchboardAddressView> getSwitchboardAddresses();

    @UpdatableMapping
    public List<SwitchboardInterfaceView> getSwitchboardInterfaces();

}

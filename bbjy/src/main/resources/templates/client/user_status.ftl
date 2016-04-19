<span class="title">我负责的客户状态图</span>

<div class="statuscontent">
    <ul>
        <li class="statusitem status1">
        <span class="statusline">
            <label style="width: ${potential_user/total_user*100}%;"></label>
        </span>
            <i></i>
            <span class="txt">${potential_user/total_user*100}% 潜在</span>
        </li>
        <li class="statusitem status2">
        <span class="statusline">
            <label style="width: ${follow_user/total_user*100}%;"></label>
        </span>
            <i></i>
            <span class="txt">${follow_user/total_user*100}% 跟进</span>
        </li>
        <li class="statusitem status3">
        <span class="statusline">
            <label style="width: ${service_user/total_user*100}%;"></label>
        </span>
            <i></i>
            <span class="txt">${service_user/total_user*100}% 成交</span>
        </li>
        <li class="statusitem status4">
        <span class="statusline">
            <label style="width: ${freeze_user/total_user*100}%;"></label>
        </span>
            <i></i>
            <span class="txt">${freeze_user/total_user*100}% 冻结</span>
        </li>
    </ul>
</div>
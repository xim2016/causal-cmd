/*
 * Copyright (C) 2017 University of Pittsburgh.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package edu.pitt.dbmi.causal.cmd.opt.algo;

import edu.pitt.dbmi.causal.cmd.ParamAttrs;
import edu.pitt.dbmi.causal.cmd.algo.AlgorithmType;
import edu.pitt.dbmi.causal.cmd.opt.CmdLongOpts;
import java.util.Collections;
import java.util.List;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

/**
 *
 * Mar 14, 2017 9:33:55 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class GFCIcCmdOption extends AbstractGFCICmdOption {

    protected double alpha;
    protected double penaltyDiscount;

    protected boolean skipUniqueVarName;
    protected boolean skipNonZeroVariance;

    public GFCIcCmdOption() {
        super();
    }

    @Override
    public void parseRequiredOptions(CommandLine cmd) throws Exception {
        // no required options
    }

    @Override
    public void parseOptionalOptions(CommandLine cmd) throws Exception {
        super.parseOptionalOptions(cmd);

        alpha = CmdLongOpts.getDouble(CmdLongOpts.ALPHA, ParamAttrs.ALPHA, cmd);
        penaltyDiscount = CmdLongOpts.getDouble(CmdLongOpts.PENALTY_DISCOUNT, ParamAttrs.PENALTY_DISCOUNT, cmd);

        skipUniqueVarName = cmd.hasOption(CmdLongOpts.SKIP_UNIQUE_VAR_NAME);
        skipNonZeroVariance = cmd.hasOption(CmdLongOpts.SKIP_NONZERO_VARIANCE);

        String prefix = String.format("%s_%s_%d", AlgorithmType.GFCIC.getCmd(), dataFile.getFileName(), System.currentTimeMillis());
        outputPrefix = cmd.getOptionValue(CmdLongOpts.OUTPUT_PREFIX, prefix);
    }

    @Override
    public List<Option> getRequiredOptions() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Option> getOptionalOptions() {
        List<Option> options = super.getOptionalOptions();
        options.add(new Option(null, CmdLongOpts.ALPHA, true, CmdLongOpts.getDescription(CmdLongOpts.ALPHA)));
        options.add(new Option(null, CmdLongOpts.PENALTY_DISCOUNT, true, CmdLongOpts.getDescription(CmdLongOpts.PENALTY_DISCOUNT)));
        options.add(new Option(null, CmdLongOpts.SKIP_UNIQUE_VAR_NAME, false, CmdLongOpts.getDescription(CmdLongOpts.SKIP_UNIQUE_VAR_NAME)));
        options.add(new Option(null, CmdLongOpts.SKIP_NONZERO_VARIANCE, false, CmdLongOpts.getDescription(CmdLongOpts.SKIP_NONZERO_VARIANCE)));

        return options;
    }

    public double getAlpha() {
        return alpha;
    }

    public double getPenaltyDiscount() {
        return penaltyDiscount;
    }

    public boolean isSkipUniqueVarName() {
        return skipUniqueVarName;
    }

    public boolean isSkipNonZeroVariance() {
        return skipNonZeroVariance;
    }

}
